package com.example.version_java.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.ArraySet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.version_java.R;
import com.example.version_java.base.BaseFragment;
import com.example.version_java.databinding.FragmentMapBinding;
import com.example.version_java.ui.criminallist.CriminalListActivity;
import com.example.version_java.ui.home.HomeViewModel;
import com.example.version_java.ui.home.HomeViewState;
import com.example.version_java.ui.login.LoginActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MapFragment extends BaseFragment {

    static final int REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE = 34;

    /**
     * 범죄자들을 지도에 나타내기 위한 리스트
     */
    private ArraySet<MapPOIItem> criminalItemList = new ArraySet<>();

    private MapViewModel mapViewModel;

    private HomeViewModel homeViewModel;

    /**
     * 카카오맵을 나타내는 변수
     */
    private MapView mapView;

    /**
     * 현재위치를 나타내는 변수
     */
    private MapPOIItem currentLocation;

    /**
     * 사람아이콘 클릭시 로그아웃, 탈퇴 팝업이 나오게 하는 변수.
     */
    private PopupMenu popupMenu;

    private Snackbar snackbar;

    private MapView.MapViewEventListener mapViewEventListener = new MapView.MapViewEventListener() {
        @Override
        public void onMapViewInitialized(MapView mapView) {

        }

        @Override
        public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
            /**
             * 현재 위치값을 계속 갱신
             */
            mapViewModel.currentCenterMapPoint.setValue(mapPoint);
        }

        @Override
        public void onMapViewZoomLevelChanged(MapView mapView, int i) {
            mapViewModel.currentZoomLevel.setValue(i);
        }

        @Override
        public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
            if (((FragmentMapBinding) getBinding()).containerPoiInfo.getVisibility() == View.VISIBLE) {
                ((FragmentMapBinding) getBinding()).containerPoiInfo.setVisibility(View.GONE);
                ((FragmentMapBinding) getBinding()).containerPoiInfo.startAnimation(AnimationUtils.loadAnimation(
                        requireContext(),
                        R.anim.slide_down
                ));
            }
        }

        @Override
        public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

        }
    };

    private MapView.POIItemEventListener poiItemEventListener = new MapView.POIItemEventListener() {
        @Override
        public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
            if (mapPOIItem.getMapPoint() != currentLocation.getMapPoint()) {
                mapViewModel.getSelectPOIItemInfo(mapPOIItem.getItemName());
            }
        }

        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

        }

        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

        }

        @Override
        public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        locationRequest();
    }

    private void initUi() {
        mapView = new MapView(requireActivity());
        mapView.setMapViewEventListener(mapViewEventListener);
        mapView.setPOIItemEventListener(poiItemEventListener);
        ((FragmentMapBinding) getBinding()).containerMap.addView(mapView);

        popupMenu = new PopupMenu(requireContext(), ((FragmentMapBinding) getBinding()).user);
        popupMenu.getMenuInflater().inflate(R.menu.menu_user, this.popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout) {
                /**
                 * 로그아웃 버튼 클릭시
                 */
                mapViewModel.logout();
            } else if (item.getItemId() == R.id.withdraw) {
                /**
                 * 탈퇴 버튼 클릭시
                 */
                mapViewModel.withdraw();
            }
            return true;
        });

        mapViewModel.showCriminals();
        mapViewModel.setCurrentLocation();
    }

    private void initViewModel() {
        mapViewModel = new ViewModelProvider(requireActivity()).get(MapViewModel.class);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);


        ((FragmentMapBinding) getBinding()).setViewModel(mapViewModel);

        mapViewModel.getViewStateLiveData().observe(getViewLifecycleOwner(), viewState -> {
            if (viewState instanceof MapViewState) {
                onChangedMapViewState((MapViewState) viewState);
            }
        });

        homeViewModel.getViewStateLiveData().observe(getViewLifecycleOwner(), viewState -> {
            if (viewState instanceof HomeViewState) {
                onChangedHomeViewState((HomeViewState) viewState);
            }
        });

    }

    /**
     * 상태에 따른 화면변화를 나타냄
     */
    @SuppressLint("NewApi")
    private void onChangedMapViewState(MapViewState viewState) {
        if (viewState instanceof MapViewState.ShowProgress) {
            /**
             * Progress Show
             */
            ((FragmentMapBinding) getBinding()).progressbar.bringToFront();
            ((FragmentMapBinding) getBinding()).progressbar.setVisibility(View.VISIBLE);
        } else if (viewState instanceof MapViewState.HideProgress) {
            /**
             * Progress Hide
             */
            ((FragmentMapBinding) getBinding()).progressbar.setVisibility(View.GONE);
        } else if (viewState instanceof MapViewState.SetCurrentLocation) {
            /**
             * 현재 위치를 나타냄
             */
            setCurrentLocation(((MapViewState.SetCurrentLocation) viewState).getMapPoint(), true);
        } else if (viewState instanceof MapViewState.RenewCurrentLocation) {
            /**
             * 현재 위치를 갱신
             */
            setCurrentLocation(((MapViewState.RenewCurrentLocation) viewState).getMapPoint(), false);
        } else if (viewState instanceof MapViewState.GetCriminalItems) {
            /**
             * 범죄자 리스트들을 모두 보여줌.
             */
            criminalItemList.addAll(((MapViewState.GetCriminalItems) viewState).getItems());

            if (mapView != null) {
                mapView.removeAllPOIItems();
                criminalItemList.forEach(mapPOIItem -> {
                    mapView.addPOIItem(mapPOIItem);
                });
            }
        } else if (viewState instanceof MapViewState.SetZoomLevel) {
            /**
             * 카카오맵 줌사이즈 관련 내용.
             */
            if (mapView != null) {
                mapView.setZoomLevel(((MapViewState.SetZoomLevel) viewState).getZoomLevel(), true);
            }
        } else if (viewState instanceof MapViewState.Error) {
            /**
             * 카카오맵 관련 에러메세지를 보여줌.
             */
            Toast.makeText(requireContext(), ((MapViewState.Error) viewState).getErrorMessage(), Toast.LENGTH_SHORT).show();
        } else if (viewState instanceof MapViewState.GetSelectPOIItem) {
            /**
             * 선택한 범죄자 정보를 가져옴.
             */
            ((FragmentMapBinding) getBinding()).containerPoiInfo.bringToFront();
            ((FragmentMapBinding) getBinding()).containerPoiInfo.setVisibility(View.VISIBLE);
            ((FragmentMapBinding) getBinding()).containerPoiInfo.startAnimation(AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.slide_up
            ));

            ((FragmentMapBinding) getBinding()).itemName.setText(((MapViewState.GetSelectPOIItem) viewState).getItem().getName());
            ((FragmentMapBinding) getBinding()).itemLocation.setText(((MapViewState.GetSelectPOIItem) viewState).getItem().getAddress());
            ((FragmentMapBinding) getBinding()).distance.setText(((MapViewState.GetSelectPOIItem) viewState).getDistance());
        } else if (viewState instanceof MapViewState.CallPolice) {
            /**
             * 112 전화.
             */
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:112"));
            startActivity(intent);
        } else if (viewState instanceof MapViewState.AroundCriminals) {
            /**
             * 반경 5km 에 범죄자를 나타내는 배너 나타냄.
             */
            if (snackbar != null) {
                snackbar.dismiss();
            }
            ((FragmentMapBinding) getBinding()).containerSnackBar.bringToFront();
            snackbar = Snackbar.make(
                    ((FragmentMapBinding) getBinding()).containerSnackBar,
                    "현재위치에서 5Km 반경에 범죄자가 " + ((MapViewState.AroundCriminals) viewState).getList().size() + "명이 있습니다.",
                    Snackbar.LENGTH_SHORT
            );
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(snackbar.getView().getLayoutParams());
            layoutParams.gravity = Gravity.TOP;
            layoutParams.topMargin = 10;
            snackbar.getView().setLayoutParams(layoutParams);
            snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE);
            snackbar.show();
        } else if (viewState instanceof MapViewState.RouteAroundCriminalList) {
            /**
             * 주변 범죄자를 확인하는 화면으로 이동.
             */
            Intent intent = new Intent(requireContext(), CriminalListActivity.class);
            intent.putExtra("key_range", mapViewModel.settingRoundCriminal);
            startActivity(intent);
        } else if (viewState instanceof MapViewState.LogoutUser) {
            /**
             * 로그아웃.
             */
            Toast.makeText(requireContext(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(requireContext(), LoginActivity.class));
            requireActivity().finish();
        } else if (viewState instanceof MapViewState.WithdrawUser) {
            /**
             * 회원탈퇴.
             */
            Toast.makeText(requireContext(), "계정이 삭제되어 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> System.exit(0), 1500L);
        } else if (viewState instanceof MapViewState.ShowUserPopupMenu) {
            /**
             * 사람 아이콘 클릭시 로그아웃,회원탈퇴 팝업이 나옴.
             */
            if (popupMenu != null) {
                popupMenu.show();
            }
        }
    }

    private void onChangedHomeViewState(HomeViewState viewState) {
        if (viewState instanceof HomeViewState.PermissionGrant) {
            initUi();
        }
    }


    private void locationRequest() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            initUi();
        } else {
            requireActivity().requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE
            );
        }
    }

    /**
     * 현재 위치를 나타내는 로직
     */
    private void setCurrentLocation(MapPoint currentMapPoint, boolean isMoveCurrentPosition) {

        /**
         * 이미 현재위치 아이콘이 있을경우 삭제하고 다시 만들기 위해 추가.
         */
        if (currentLocation != null) {
            mapView.removePOIItem(currentLocation);
        }

        /**
         * 현재위치 아이콘 추가.
         */
        currentLocation = new MapPOIItem();
        currentLocation.setItemName("Current Location");
        currentLocation.setMapPoint(currentMapPoint);
        currentLocation.setMarkerType(MapPOIItem.MarkerType.RedPin);
        currentLocation.setShowAnimationType(MapPOIItem.ShowAnimationType.SpringFromGround);

        /**
         * 카카오맵에 적용.
         */
        if (mapView != null) {
            mapView.addPOIItem(currentLocation);
            if (isMoveCurrentPosition) {
                mapView.setMapCenterPoint(currentMapPoint, false);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapViewModel.finishRenewThread();
    }

    public MapFragment() {
        super(R.layout.fragment_map);
        this.setBinding((FragmentMapBinding) this.getBinding());
    }
}
