package com.example.crimenotification.ui.map

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseFragment
import com.example.crimenotification.base.ViewState
import com.example.crimenotification.databinding.FragmentMapBinding
import com.example.crimenotification.ext.*
import com.example.crimenotification.ui.criminallist.CriminalListActivity
import com.example.crimenotification.ui.criminallist.CriminalListActivity.Companion.KEY_RANGE
import com.example.crimenotification.ui.home.HomeViewModel
import com.example.crimenotification.ui.home.HomeViewState
import com.example.crimenotification.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import kotlin.system.exitProcess

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    /**
     * 범죄자들을 지도에 나타내기 위한 리스트
     */
    private val criminalItemList = mutableSetOf<MapPOIItem>()

    private val mapViewModel by viewModels<MapViewModel>()

    private val homeViewModel by activityViewModels<HomeViewModel>()

    /**
     * 현재위치를 나타내는 변수
     */
    private lateinit var currentLocation: MapPOIItem

    /**
     * 카카오맵을 나타내는 변수
     */
    private var mapview: MapView? = null

    /**
     * 사람아이콘 클릭시 로그아웃, 탈퇴 팝업이 나오게 하는 변수.
     */
    private val userPopupMenu by lazy {
        PopupMenu(requireContext(), binding.user).apply {
            menuInflater.inflate(R.menu.menu_user, this.menu)
            setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    /**
                     * 로그아웃 버튼 클릭시
                     */
                    R.id.logout -> {

                        mapViewModel.logout()
                    }

                    /**
                     * 탈퇴 버튼 클릭시
                     */
                    R.id.withdraw -> {
                        mapViewModel.withdraw()
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    /**
     * 카카오맵의 맵기능을 나타내는 콜백리스너
     */
    private val mapViewEventListener =
        object : MapView.MapViewEventListener {
            override fun onMapViewInitialized(p0: MapView?) {

            }

            override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
                /**
                 * 현재 위치값을 계속 갱신
                 */
                mapViewModel.currentCenterMapPoint.value = p1
            }

            override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
                mapViewModel.currentZoomLevel.value = p1
            }

            override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
                /**
                 * 지도를 드래스 했을시에 범죄자 정보가 아래로 닫혀지게 구성.
                 */
                binding.containerPoiInfo.hidePOIInfoContainer(requireContext())
            }

            override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {

            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationRequest()
        initViewModel()
    }

    /**
     * 지도에 나타나는 아이콘(범죄자) 클릭시 정보를 가져오는 콜백리스너
     */
    private val poiItemEventListener = object : MapView.POIItemEventListener {
        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
            p1?.let { item ->
                if (item.mapPoint != currentLocation.mapPoint) {
                    mapViewModel.getSelectPOIItemInfo(item.itemName)
                }
            }
        }

        override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
        ) {
        }

        override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
        }
    }

    private fun initUi() {
        mapview = MapView(requireActivity()).apply {
            setMapViewEventListener(this@MapFragment.mapViewEventListener)
            setPOIItemEventListener(this@MapFragment.poiItemEventListener)
        }
        binding.containerMap.addView(mapview)
        mapViewModel.showCriminals()
        mapViewModel.setCurrentLocation()
    }

    private fun initViewModel() {
        binding.viewModel = mapViewModel

        mapViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState: ViewState? ->
            (viewState as? MapViewState)?.let { onChangedMapViewState(viewState) }
        }

        homeViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState: ViewState? ->
            (viewState as? HomeViewState)?.let { onChangedHomeViewState(viewState) }
        }
    }


    /**
     * 현재 위치를 나타내는 로직
     */
    private fun setCurrentLocation(currentMapPoint: MapPoint, isMoveCurrentPosition: Boolean) {

        /**
         * 이미 현재위치 아이콘이 있을경우 삭제하고 다시 만들기 위해 추가.
         */
        if (::currentLocation.isInitialized) {
            mapview?.removePOIItem(currentLocation)
        }


        /**
         * 현재위치 아이콘 추가.
         */
        currentLocation = MapPOIItem().apply {
            itemName = "Current Location"
            mapPoint = currentMapPoint
            markerType = MapPOIItem.MarkerType.RedPin
            showAnimationType = MapPOIItem.ShowAnimationType.SpringFromGround
        }

        /**
         * 카카오맵에 적용.
         */
        mapview?.apply {
            addPOIItem(currentLocation)
            if (isMoveCurrentPosition) {
                setMapCenterPoint(currentMapPoint, false)
            }
        }
    }

    private fun onChangedHomeViewState(viewState: ViewState) {

        when (viewState) {
            /**
             * 권한이 수락되었을 경우 카카오맵이 연동.
             */
            is HomeViewState.PermissionGrant -> {
                initUi()
            }
        }
    }

    /**
     * 상태에 따른 화면변화를 나타냄
     */
    private fun onChangedMapViewState(viewState: ViewState) {
        when (viewState) {
            /**
             * 현재 위치를 나타냄
             */
            is MapViewState.SetCurrentLocation -> {
                setCurrentLocation(viewState.mapPoint, true)
            }
            /**
             * 현재 위치를 갱신
             */
            is MapViewState.RenewCurrentLocation -> {
                setCurrentLocation(viewState.mapPoint, false)
            }

            /**
             * 범죄자 리스트들을 모두 보여줌.
             */
            is MapViewState.GetCriminalItems -> {
                criminalItemList.addAll(viewState.items)
                mapview?.apply {
                    removeAllPOIItems()
                    addPOIItems(viewState.items)
                }
            }

            /**
             * 카카오맵 줌사이즈 관련 내용.
             */
            is MapViewState.SetZoomLevel -> {
                mapview?.setZoomLevel(viewState.zoomLevel, true)
            }

            /**
             * 카카오맵 관련 에러메세지를 보여줌.
             */
            is MapViewState.Error -> {
                showToast(message = viewState.errorMessage)
            }

            /**
             * 선택한 범죄자 정보를 가져옴.
             */
            is MapViewState.GetSelectPOIItem -> {
                with(binding) {
                    containerPoiInfo.showPOIInfoContainer(requireContext())
                    itemName.text = viewState.item.name
                    itemLocation.text = viewState.item.address
                    distance.text = viewState.distance
                }
            }

            /**
             * Progress Show
             */
            is MapViewState.ShowProgress -> {
                binding.progressbar.bringToFront()
                binding.progressbar.isVisible = true
            }

            /**
             * Progress Hide
             */
            is MapViewState.HideProgress -> {
                binding.progressbar.isVisible = false
            }

            /**
             * 112 전화.
             */
            is MapViewState.CallPolice -> {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:112")
                }
                startActivity(intent)
            }

            /**
             * 반경 5km 에 범죄자를 나타내는 배너 나타냄.
             */
            is MapViewState.AroundCriminals -> {
                showSnackBar(
                    attachLayout = binding.containerSnackBar, message = """
                        현재위치에서 5Km 반경에 범죄자가 ${viewState.list.size}명이 있습니다.
                    """.trimIndent()
                )
            }

            /**
             * 주변 범죄자를 확인하는 화면으로 이동.
             */
            is MapViewState.RouteAroundCriminalList -> {
                val intent = Intent(requireContext(), CriminalListActivity::class.java).apply {
                    putExtra(KEY_RANGE, mapViewModel.settingRoundCriminal)
                }
                startActivity(intent)
            }

            /**
             * 로그아웃.
             */
            is MapViewState.LogoutUser -> {
                showToast(message = "로그아웃되었습니다.")
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }

            /**
             * 회원탈퇴
             */
            is MapViewState.WithdrawUser -> {
                showToast(message = "계정이 삭제되어 앱이 종료됩니다.")
                Handler(Looper.getMainLooper()).postDelayed({ exitProcess(0) }, 2000L)
            }

            /**
             * 사람 아이콘 클릭시 로그아웃,회원탈퇴 팝업이 나옴.
             */
            is MapViewState.ShowUserPopupMenu -> {
                userPopupMenu.show()
            }
        }
    }

    /**
     * Gps 등 Location 권한 요청.
     */
    private fun locationRequest() {
        val permissionApproved =
            requireActivity().hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionApproved) {
            initUi()
        } else {
            val provideRationale = shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
            if (provideRationale) {
                initUi()
            } else {
                requireActivity().requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }

    companion object {
        const val REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE = 34
    }

}
