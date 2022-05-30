package com.example.version_java.ui.map;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.version_java.R;
import com.example.version_java.base.BaseViewModel;
import com.example.version_java.data.repo.CriminalRepository;
import com.example.version_java.data.repo.FirebaseRepository;
import com.example.version_java.room.entity.CriminalEntity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MapViewModel extends BaseViewModel {

    private final FirebaseRepository firebaseRepository;
    private final CriminalRepository criminalRepository;

    MutableLiveData<MapPoint> currentCenterMapPoint = new MutableLiveData<>();
    MutableLiveData<Integer> currentZoomLevel = new MutableLiveData<>();

    int settingRoundCriminal = 5000;

    private static final Long RENEW_CURRENT_LOCATION_INTERVAL = 5000L;

    private Thread renewThread;


    /**
     * 현재 위치로 이동하는 로직.
     * 현재위치 아이콘 클릭시 실행.
     */
    public void setCurrentLocation() {

    }

    /**
     * 주변에 범죄자가 몇명있는지 화면으로 이동.
     */
    public void aroundCriminalList() {
        viewStateChanged(MapViewState.RouteAroundCriminalList.INSTANCE);
    }

    /**
     * 회원탈퇴.
     */
    public void withdraw() {
        firebaseRepository.delete(isDelete -> {
            if ((Boolean) isDelete) {
                viewStateChanged(MapViewState.WithdrawUser.INSTANCE);
            } else {
                viewStateChanged(new MapViewState.Error("회원탈퇴를 실패하였습니다."));
            }
            return null;
        });
    }

    /**
     * 카카오맵에 저장한 범죄자들을 보여주는 로직.
     */
    @SuppressLint("NewApi")
    public void showCriminals() {
        viewStateChanged(MapViewState.ShowProgress.INSTANCE);
        new Thread(() -> {
            criminalRepository.getLocalCriminals(
                    onSuccess -> {
                        List<CriminalEntity> entityList = (List<CriminalEntity>) onSuccess;

                        ArrayList<MapPOIItem> mapPOIItems = new ArrayList<>();

                        entityList.forEach(criminalEntity -> {
                            MapPOIItem mapPOIItem = new MapPOIItem();
                            mapPOIItem.setItemName(criminalEntity.getName());
                            mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(criminalEntity.getLatitude(), criminalEntity.getLongitude()));
                            mapPOIItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                            mapPOIItem.setCustomImageResourceId(R.drawable.image);
                            mapPOIItems.add(mapPOIItem);
                        });

                        viewStateChanged(new MapViewState.GetCriminalItems((MapPOIItem[]) mapPOIItems.toArray()));
                        viewStateChanged(MapViewState.HideProgress.INSTANCE);
                        return null;
                    },
                    onFailure -> {
                        viewStateChanged(new MapViewState.Error("범죄자 데이터 호출에 실패하였습니다."));
                        viewStateChanged(MapViewState.HideProgress.INSTANCE);
                        return null;
                    }
            );

        }).start();
    }

    /**
     * 로그아웃
     */
    public void logout() {
        if (firebaseRepository.logout()) {
            viewStateChanged(MapViewState.LogoutUser.INSTANCE);
        } else {
            viewStateChanged(new MapViewState.Error("로그아웃이 실패하였습니다."));
        }
    }

    /**
     * 112 전화.
     */
    public void callPolice() {
        viewStateChanged(MapViewState.CallPolice.INSTANCE);
    }

    public void getSelectPOIItemInfo(String itemName) {
        new Thread(() -> {
            criminalRepository.getCriminalEntity(itemName,
                    onSuccess -> {
                        CriminalEntity entity = (CriminalEntity) onSuccess;

                        return null;
                    },
                    onFailure -> {
                        String message = (String) onFailure;
                        viewStateChanged(new MapViewState.Error(message));
                        return null;
                    });
        }).start();
    }

    /**
     * 사람 아이콘 클릭시 팝업을 보여줌.
     */
    public void showUserPopupMenu() {
        viewStateChanged(MapViewState.ShowUserPopupMenu.INSTANCE);
    }


    private void renewCurrentLocation() {

    }


    @Inject
    MapViewModel(
            @NotNull Application application,
            CriminalRepository criminalRepository,
            FirebaseRepository firebaseRepository
    ) {
        super(application);
        this.criminalRepository = criminalRepository;
        this.firebaseRepository = firebaseRepository;
    }
}
