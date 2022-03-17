package com.example.crimenotification.data.model

import com.example.crimenotification.R
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint

data class CriminalItem(
    var name: String = "",
    var addressResident: String = "",
    var addressReal: String = "",
    var longitude: Double = 0.0,
    var latitude: Double = 0.0
) {
    fun toMapPOIItem(): MapPOIItem {
        return MapPOIItem().apply {
            itemName = name
            mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            markerType = MapPOIItem.MarkerType.CustomImage
            customImageResourceId = R.drawable.image
        }
    }
}