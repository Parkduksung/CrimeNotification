package com.example.crimenotification.ui.map

import com.example.crimenotification.base.ViewState
import com.example.crimenotification.room.entity.CriminalEntity
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint

sealed class MapViewState : ViewState {
    data class SetZoomLevel(val zoomLevel: Int) : MapViewState()
    data class RenewCurrentLocation(val mapPoint: MapPoint) : MapViewState()
    data class SetCurrentLocation(val mapPoint: MapPoint) : MapViewState()
    data class GetCriminalItems(val items: Array<MapPOIItem>) : MapViewState()
    data class GetSelectPOIItem(val item: CriminalEntity, val distance: String) : MapViewState()
    data class Error(val errorMessage: String) : MapViewState()
    data class AroundCriminals(val list: List<CriminalEntity>) : MapViewState()
    object ShowProgress : MapViewState()
    object WithdrawUser : MapViewState()
    object HideProgress : MapViewState()
    object CallPolice : MapViewState()
    object RouteAroundCriminalList : MapViewState()
}