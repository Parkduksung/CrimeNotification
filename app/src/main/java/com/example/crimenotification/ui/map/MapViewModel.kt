package com.example.crimenotification.ui.map

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.crimenotification.base.BaseViewModel
import com.example.crimenotification.data.repo.CriminalRepository
import com.example.crimenotification.data.repo.FirebaseRepository
import com.example.crimenotification.ext.defaultScope
import com.example.crimenotification.ext.ioScope
import com.example.crimenotification.ext.uiScope
import com.example.crimenotification.util.DistanceManager
import com.example.crimenotification.util.GpsTracker
import com.example.crimenotification.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import net.daum.mf.map.api.MapPoint
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    app: Application,
    private val criminalRepository: CriminalRepository,
    private val firebaseRepository: FirebaseRepository
) : BaseViewModel(app) {

    val currentCenterMapPoint = MutableLiveData<MapPoint>()

    val currentZoomLevel = MutableLiveData<Int>()

    private val gpsTracker = GpsTracker(app)

    fun setCurrentLocation() {
        ioScope {
            viewStateChanged(MapViewState.ShowProgress)
            when (val result = gpsTracker.getLocation()) {
                is Result.Success -> {
                    result.data.addOnCompleteListener { task ->
                        val location = task.result

                        val resultMapPoint =
                            MapPoint.mapPointWithGeoCoord(location.latitude, location.longitude)

                        viewStateChanged(MapViewState.SetCurrentLocation(resultMapPoint))
                        viewStateChanged(MapViewState.HideProgress)
                        viewStateChanged(MapViewState.SetZoomLevel(4))
                    }
                }

                is Result.Error -> {
                    viewStateChanged(MapViewState.Error(result.exception.message.toString()))
                    viewStateChanged(MapViewState.HideProgress)
                }
            }
        }
    }

    fun withdraw() {
        ioScope {
            firebaseRepository.delete()?.addOnCompleteListener {
                if (it.isSuccessful) {
                    viewStateChanged(MapViewState.WithdrawUser)
                } else {

                }
            }
        }
    }

    fun showCriminals() {
        viewStateChanged(MapViewState.ShowProgress)
        ioScope {
            when (val result = criminalRepository.getLocalCriminals()) {
                is Result.Success -> {
                    val toMapPOIItem = result.data.map { it.toCriminalItem().toMapPOIItem() }
                    viewStateChanged(MapViewState.GetCriminalItems(toMapPOIItem.toTypedArray()))
                }

                is Result.Error -> {

                }
            }
            viewStateChanged(MapViewState.HideProgress)
        }
    }

    fun callPolice() {
        viewStateChanged(MapViewState.CallPolice)
    }

    fun getSelectPOIItemInfo(itemName: String) {
        ioScope {
            when (val criminalResult = criminalRepository.getCriminalEntity(itemName)) {
                is Result.Success -> {
                    ioScope {
                        when (val locationResult = gpsTracker.getLocation()) {
                            is Result.Success -> {
                                locationResult.data.addOnCompleteListener { task ->
                                    val location = task.result
                                    defaultScope {
                                        val distance = DistanceManager.getDistance(
                                            lat1 = location.latitude,
                                            lon1 = location.longitude,
                                            lat2 = criminalResult.data.latitude,
                                            lon2 = criminalResult.data.longitude
                                        )
                                        uiScope {
                                            viewStateChanged(
                                                MapViewState.GetSelectPOIItem(
                                                    criminalResult.data,
                                                    DistanceManager.toStringDistance(distance)
                                                )
                                            )
                                        }
                                    }
                                }
                            }

                            is Result.Error -> {
                                viewStateChanged(MapViewState.Error(locationResult.exception.message.toString()))
                            }
                        }
                    }
                }

                is Result.Error -> {
                    viewStateChanged(MapViewState.Error(criminalResult.exception.message.toString()))
                }
            }
        }
    }
}