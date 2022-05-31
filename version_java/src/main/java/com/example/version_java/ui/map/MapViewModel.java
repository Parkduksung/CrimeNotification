package com.example.version_java.ui.map;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;

import androidx.lifecycle.MutableLiveData;

import com.example.version_java.R;
import com.example.version_java.base.BaseViewModel;
import com.example.version_java.data.repo.CriminalRepository;
import com.example.version_java.data.repo.FirebaseRepository;
import com.example.version_java.room.entity.CriminalEntity;
import com.example.version_java.util.DistanceManager;
import com.example.version_java.util.GpsTracker;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MapViewModel extends BaseViewModel {

    private final FirebaseRepository firebaseRepository;
    private final CriminalRepository criminalRepository;

    private final GpsTracker gpsTracker;

    MutableLiveData<MapPoint> currentCenterMapPoint = new MutableLiveData<>();
    MutableLiveData<Integer> currentZoomLevel = new MutableLiveData<>();

    int settingRoundCriminal = 5000;

    private static final Long RENEW_CURRENT_LOCATION_INTERVAL = 5000L;

    private Thread renewThread;

    private Thread searchThread;

    /**
     * 현재 위치로 이동하는 로직.
     * 현재위치 아이콘 클릭시 실행.
     */
    public void setCurrentLocation() {

        finishRenewThread();

        gpsTracker.getLocation(callback -> {
            if (callback instanceof Location) {
                Location location = (Location) callback;
                MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(location.getLatitude(), location.getLongitude());
                viewStateChanged(new MapViewState.SetCurrentLocation(mapPoint));
            } else if (callback instanceof String) {
                viewStateChanged(new MapViewState.Error("현재 위치를 가져오는데 실패하였습니다."));
            }
            return null;
        });
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
                finishRenewThread();
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
                            mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(criminalEntity.getLongitude(), criminalEntity.getLatitude()));
                            mapPOIItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                            mapPOIItem.setCustomImageResourceId(R.drawable.image);
                            mapPOIItems.add(mapPOIItem);
                        });

                        viewStateChanged(new MapViewState.GetCriminalItems(mapPOIItems));
                        viewStateChanged(MapViewState.HideProgress.INSTANCE);

                        renewCurrentLocation();
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
            finishRenewThread();
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

    /**
     * 범죄자 아이콘을 선택하였을때의 로직.
     * 범죄자 이름을 파라메터로 받음.
     */
    public void getSelectPOIItemInfo(String itemName) {
        new Thread(() -> {
            /**
             * 파라메터로 입력받은 범죄자의 정보를 로컬 DB 에서 가져옴.
             */
            criminalRepository.getCriminalEntity(itemName,
                    onSuccess -> {
                        CriminalEntity entity = (CriminalEntity) onSuccess;

                        /**
                         * 현재 위치를 가져옴.
                         */
                        gpsTracker.getLocation(callback -> {

                            if (callback instanceof Location) {
                                Location location = (Location) callback;

                                /**
                                 * 가져온 현재 위치와 로컬DB에서 검색한 범죄자의 거리를 계산하여
                                 * 거리와 검색한 범죄자를 보여줌.
                                 */
                                int distance = DistanceManager.INSTANCE.getDistance(
                                        location.getLatitude(),
                                        location.getLongitude(),
                                        entity.getLongitude(),
                                        entity.getLatitude()
                                );

                                viewStateChanged(
                                        new MapViewState.GetSelectPOIItem(
                                                entity,
                                                DistanceManager.INSTANCE.toStringDistance(distance)
                                        )
                                );
                            } else if (callback instanceof String) {
                                /**
                                 * 현재 위치를 가져오지 못한 경우.
                                 */
                                viewStateChanged(new MapViewState.Error("현재 위치를 가져오는데 실패하였습니다."));
                            }

                            return null;
                        });

                        return null;
                    },
                    onFailure -> {
                        /**
                         * 검색한 범죄자를 가져오지 못한 경우.
                         */
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


    @SuppressLint("NewApi")
    private void renewCurrentLocation() {
        renewThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(RENEW_CURRENT_LOCATION_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                gpsTracker.getLocation(callback -> {
                    if (callback instanceof Location) {
                        Location location = (Location) callback;
                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(location.getLatitude(), location.getLongitude());

                        searchThread = new Thread(() -> {
                            criminalRepository.getLocalCriminals(
                                    onSuccess -> {
                                        List<CriminalEntity> entityList = (List<CriminalEntity>) onSuccess;
                                        List<CriminalEntity> toFilterEntity = new ArrayList<>();
                                        entityList.forEach(criminalEntity -> {
                                            int distance = DistanceManager.INSTANCE.getDistance(
                                                    location.getLatitude(),
                                                    location.getLongitude(),
                                                    criminalEntity.getLongitude(),
                                                    criminalEntity.getLatitude()
                                            );

                                            if (distance <= 5000) {
                                                toFilterEntity.add(criminalEntity);
                                            }
                                        });
                                        if (!toFilterEntity.isEmpty()) {
                                            viewStateChanged(new MapViewState.AroundCriminals(toFilterEntity));
                                        }

                                        viewStateChanged(new MapViewState.RenewCurrentLocation(mapPoint));


                                        viewStateChanged(MapViewState.HideProgress.INSTANCE);
                                        return null;
                                    },
                                    onFailure -> {
                                        viewStateChanged(new MapViewState.Error("범죄자 데이터 호출에 실패하였습니다."));
                                        viewStateChanged(MapViewState.HideProgress.INSTANCE);
                                        return null;
                                    }
                            );
                        });
                        searchThread.start();
                    } else if (callback instanceof String) {
                        viewStateChanged(new MapViewState.Error("현재 위치를 가져오는데 실패하였습니다."));
                    }
                    return null;
                });

            }
        });

        renewThread.start();
    }

    public void finishRenewThread() {
        if (renewThread != null && renewThread.isAlive()) {
            try {
                renewThread.stop();
                searchThread.stop();
            } catch (Exception e) {
            }
        }
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
        gpsTracker = new GpsTracker(application);
    }
}
