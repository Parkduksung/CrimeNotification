package com.example.crimenotification.ui.map

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseFragment
import com.example.crimenotification.base.ViewState
import com.example.crimenotification.databinding.FragmentMapBinding
import com.example.crimenotification.ext.hasPermission
import com.example.crimenotification.ext.hidePOIInfoContainer
import com.example.crimenotification.ext.showPOIInfoContainer
import com.example.crimenotification.ext.showToast
import com.example.crimenotification.ui.home.HomeViewModel
import com.example.crimenotification.ui.home.HomeViewState
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    private val campingItemList = mutableSetOf<MapPOIItem>()

    private val mapViewModel by viewModels<MapViewModel>()

    private val homeViewModel by activityViewModels<HomeViewModel>()

    private lateinit var currentLocation: MapPOIItem

    private var mapview: MapView? = null

    private val mapViewEventListener =
        object : MapView.MapViewEventListener {
            override fun onMapViewInitialized(p0: MapView?) {

            }

            override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
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


    private fun setCurrentLocation(currentMapPoint: MapPoint, isMoveCurrentPosition: Boolean) {
        if (::currentLocation.isInitialized) {
            mapview?.removePOIItem(currentLocation)
        }

        currentLocation = MapPOIItem().apply {
            itemName = "Current Location"
            mapPoint = currentMapPoint
            markerType = MapPOIItem.MarkerType.RedPin
            showAnimationType = MapPOIItem.ShowAnimationType.SpringFromGround
        }

        mapview?.apply {
            addPOIItem(currentLocation)
            if (isMoveCurrentPosition) {
                setMapCenterPoint(currentMapPoint, false)
            }
        }
    }

    private fun onChangedHomeViewState(viewState: ViewState) {

        when (viewState) {
            is HomeViewState.PermissionGrant -> {
                initUi()
            }
        }
    }

    private fun onChangedMapViewState(viewState: ViewState) {
        when (viewState) {
            is MapViewState.SetCurrentLocation -> {
                setCurrentLocation(viewState.mapPoint, true)
            }
            is MapViewState.RenewCurrentLocation -> {
                setCurrentLocation(viewState.mapPoint, false)
            }

            is MapViewState.GetCriminalItems -> {
                campingItemList.addAll(viewState.items)
                mapview?.apply {
                    removeAllPOIItems()
                    addPOIItems(viewState.items)
                }
            }

            is MapViewState.SetZoomLevel -> {
                mapview?.setZoomLevel(viewState.zoomLevel, true)
            }

            is MapViewState.Error -> {
                showToast(message = viewState.errorMessage)
            }

            is MapViewState.GetSelectPOIItem -> {
                with(binding) {
                    containerPoiInfo.showPOIInfoContainer(requireContext())
                    itemName.text = viewState.item.name
                    itemLocation.text = viewState.item.address
                    distance.text = viewState.distance
                }
            }

            is MapViewState.ShowProgress -> {
                binding.progressbar.bringToFront()
                binding.progressbar.isVisible = true
            }

            is MapViewState.HideProgress -> {
                binding.progressbar.isVisible = false
            }

            is MapViewState.CallPolice -> {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:112")
                }
                startActivity(intent)
            }

            is MapViewState.AroundCriminals -> {

            }

            is MapViewState.WithdrawUser -> {
//                showToast(message = "회원탈퇴되어 앱이 종료됩니다.")
//                Handler(Looper.getMainLooper()).postDelayed(
//                    { exitProcess(0) }, 1000L
//                )
            }
        }
    }

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
