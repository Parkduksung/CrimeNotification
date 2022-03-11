package com.example.crimenotification.ui.map

import com.example.crimenotification.base.ViewState
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint

sealed class MapViewState : ViewState {
        data class SetZoomLevel(val zoomLevel: Int) : MapViewState()
        data class SetCurrentLocation(val mapPoint: MapPoint) : MapViewState()
        data class GetCriminalItems(val items: Array<MapPOIItem>) : MapViewState()
//        data class GetSelectPOIItem(val item: GolfEntity) : MapViewState()
        data class Error(val errorMessage: String) : MapViewState()
        object ShowProgress : MapViewState()
        object HideProgress : MapViewState()
    }