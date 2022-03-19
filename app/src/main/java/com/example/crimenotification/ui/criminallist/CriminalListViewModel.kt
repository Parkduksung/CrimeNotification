package com.example.crimenotification.ui.criminallist

import android.app.Application
import androidx.databinding.ObservableField
import com.example.crimenotification.base.BaseViewModel
import com.example.crimenotification.data.model.DistanceCriminal
import com.example.crimenotification.data.repo.CriminalRepository
import com.example.crimenotification.ext.ioScope
import com.example.crimenotification.util.DistanceManager
import com.example.crimenotification.util.GpsTracker
import com.example.crimenotification.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class CriminalListViewModel @Inject constructor(
    app: Application,
    private val criminalRepository: CriminalRepository
) : BaseViewModel(app) {

    val rangeObservableField = ObservableField<Int>()

    private val gpsTracker = GpsTracker(app)

    fun getCriminalList() {
        ioScope {
            while (true) {
                viewStateChanged(CriminalListViewState.ShowProgress)
                delay(1000L)
                when (val result = gpsTracker.getLocation()) {
                    is Result.Success -> {
                        result.data.addOnCompleteListener { task ->
                            val location = task.result
                            ioScope {
                                when (val criminalResult = criminalRepository.getLocalCriminals()) {
                                    is Result.Success -> {

                                        val toAroundList = criminalResult.data.filter {
                                            DistanceManager.getDistance(
                                                lat1 = location.latitude,
                                                lon1 = location.longitude,
                                                lat2 = it.latitude,
                                                lon2 = it.longitude
                                            ) <= rangeObservableField.get()!!
                                        }.map {
                                            DistanceCriminal(
                                                name = it.name,
                                                address = it.address,
                                                distance = DistanceManager.getDistance(
                                                    lat1 = location.latitude,
                                                    lon1 = location.longitude,
                                                    lat2 = it.latitude,
                                                    lon2 = it.longitude
                                                )
                                            )
                                        }
                                        viewStateChanged(
                                            CriminalListViewState.RenewCriminalList(
                                                toAroundList
                                            )
                                        )
                                        viewStateChanged(CriminalListViewState.HideProgress)
                                    }

                                    is Result.Error -> {
                                        viewStateChanged(CriminalListViewState.HideProgress)
                                    }
                                }
                            }
                        }
                    }
                    is Result.Error -> {
                        viewStateChanged(CriminalListViewState.Error(result.exception.message.toString()))
                        viewStateChanged(CriminalListViewState.HideProgress)
                    }
                }
                delay(RENEW_INTERVAL)
            }
        }
    }

    companion object{
        private const val RENEW_INTERVAL = 5000L
    }
}