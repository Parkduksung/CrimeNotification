package com.example.version_java.ui.criminallist;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;

import androidx.databinding.ObservableField;

import com.example.version_java.base.BaseViewModel;
import com.example.version_java.data.model.DistanceCriminal;
import com.example.version_java.data.repo.CriminalRepository;
import com.example.version_java.room.entity.CriminalEntity;
import com.example.version_java.util.DistanceManager;
import com.example.version_java.util.GpsTracker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CriminalListViewModel extends BaseViewModel {


    private final CriminalRepository criminalRepository;

    /**
     * 현재 위치를 가져오는 GpsTracker
     */
    private final GpsTracker gpsTracker;


    /**
     * 현재 위치에서 어느정도 범위까지 가져올지 범위를 설정하는 변수.
     */
    public ObservableField<Integer> rangeObservableField = new ObservableField<>();

    private static final Long DELAY_PROGRESS = 1000L;
    private static final Long RENEW_INTERVAL = 5000L;


    /**
     * 5초마다 범죄자들을 가져오는 로직
     */
    @SuppressLint("NewApi")
    public void getCriminalList() {

        /**
         * 무한루프로 구성.
         */
        new Thread(() -> {
            while (true) {
                /**
                 * 1초동안 프로그래스바 보여줌.
                 */
                try {
                    viewStateChanged(CriminalListViewState.ShowProgress.INSTANCE);
                    Thread.sleep(DELAY_PROGRESS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                gpsTracker.getLocation(callback -> {
                    if (callback instanceof Location) {
                        Location location = (Location) callback;
                        /**
                         * 디비에 저장되어있는 범죄자들 가져옴.
                         */
                        new Thread(() -> {
                            criminalRepository.getLocalCriminals(
                                    onSuccess -> {
                                        List<CriminalEntity> entityList = (List<CriminalEntity>) onSuccess;
                                        List<DistanceCriminal> toFilterEntity = new ArrayList<>();
                                        entityList.forEach(criminalEntity -> {
                                            int distance = DistanceManager.INSTANCE.getDistance(
                                                    location.getLatitude(),
                                                    location.getLongitude(),
                                                    criminalEntity.getLongitude(),
                                                    criminalEntity.getLatitude()
                                            );

                                            if (distance <= rangeObservableField.get()) {
                                                DistanceCriminal distanceCriminal = new DistanceCriminal(criminalEntity.getName(), criminalEntity.getAddress(), distance);
                                                toFilterEntity.add(distanceCriminal);
                                            }
                                        });

                                        if (!toFilterEntity.isEmpty()) {
                                            viewStateChanged(new CriminalListViewState.RenewCriminalList(toFilterEntity));
                                        } else {
                                            viewStateChanged(CriminalListViewState.EmptyCriminalList.INSTANCE);
                                        }
                                        viewStateChanged(CriminalListViewState.HideProgress.INSTANCE);
                                        return null;
                                    },
                                    onFailure -> {
                                        viewStateChanged(new CriminalListViewState.Error("범죄자 데이터 호출에 실패하였습니다."));
                                        viewStateChanged(CriminalListViewState.HideProgress.INSTANCE);
                                        return null;
                                    }
                            );
                        }).start();
                    } else if (callback instanceof String) {
                        viewStateChanged(new CriminalListViewState.Error("현재 위치를 가져오는데 실패하였습니다."));
                    }
                    return null;
                });


                try {
                    Thread.sleep(RENEW_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    @Inject
    CriminalListViewModel(
            @NotNull Application application,
            CriminalRepository criminalRepository
    ) {
        super(application);
        this.criminalRepository = criminalRepository;
        gpsTracker = new GpsTracker(application);
    }


}
