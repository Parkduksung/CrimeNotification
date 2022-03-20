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

    /**
     * 현재 위치에서 어느정도 범위까지 가져올지 범위를 설정하는 변수.
     */
    val rangeObservableField = ObservableField<Int>()

    /**
     * 현재 위치를 가져오는 GpsTracker
     */
    private val gpsTracker = GpsTracker(app)

    /**
     * 5초마다 범죄자들을 가져오는 로직
     */
    fun getCriminalList() {
        ioScope {
            /**
             * 무한루프로 구성.
             */
            while (true) {
                /**
                 * 1초동안 프로그래스바 보여줌.
                 */
                viewStateChanged(CriminalListViewState.ShowProgress)
                delay(DELAY_PROGRESS)

                /**
                 * 현재위치 가져옴.
                 */
                when (val result = gpsTracker.getLocation()) {
                    is Result.Success -> {
                        result.data.addOnCompleteListener { task ->
                            val location = task.result
                            ioScope {
                                /**
                                 * 디비에 저장되어있는 범죄자들 가져옴.
                                 */
                                when (val criminalResult = criminalRepository.getLocalCriminals()) {
                                    is Result.Success -> {

                                        /**
                                         * 현재위치에서 범죄자들의 거리가 rangeObservableField 이내에 있는 리스트 구성.
                                         */
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


                                        if (toAroundList.isNotEmpty()) {
                                            /**
                                             * 범죄자리스트 있는경우
                                             */
                                            viewStateChanged(
                                                CriminalListViewState.RenewCriminalList(
                                                    toAroundList
                                                )
                                            )
                                        } else {
                                            /**
                                             * 범죄자리스트 없는경우
                                             */
                                            viewStateChanged(CriminalListViewState.EmptyCriminalList)
                                        }
                                        viewStateChanged(CriminalListViewState.HideProgress)
                                    }

                                    /**
                                     * 로컬에 저장되어있는 범죄자를 가져오지 못한 경우.
                                     */
                                    is Result.Error -> {
                                        viewStateChanged(CriminalListViewState.HideProgress)
                                    }
                                }
                            }
                        }
                    }

                    /**
                     * 현재 위치를 가져오지 못한 경우.
                     */
                    is Result.Error -> {
                        viewStateChanged(CriminalListViewState.Error(result.exception.message.toString()))
                        viewStateChanged(CriminalListViewState.HideProgress)
                    }
                }
                /**
                 * 몇초 간격으로 호출할지 설정.
                 */
                delay(RENEW_INTERVAL)
            }
        }
    }

    companion object {
        private const val DELAY_PROGRESS = 1000L
        private const val RENEW_INTERVAL = 5000L
    }
}