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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import net.daum.mf.map.api.MapPoint
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    app: Application,
    private val criminalRepository: CriminalRepository,
    private val firebaseRepository: FirebaseRepository
) : BaseViewModel(app) {

    /**
     * 현재 위치 좌표를 나타내는 변수
     */
    val currentCenterMapPoint = MutableLiveData<MapPoint>()

    /**
     * 현재 줌레벨을 나타내는 변수
     */
    val currentZoomLevel = MutableLiveData<Int>()

    /**
     * 반경 몇 KM 의 범죄자를 가져올지 범위 설정하는 변수.
     */
    var settingRoundCriminal: Int = 5000

    /**
     * 현재 위치 갱신하는 작업.
     */
    private lateinit var renewJob: Job

    /**
     * 현재 위치를 가져오는 변수.
     */
    private val gpsTracker = GpsTracker(app)

    /**
     * 현재 위치로 이동하는 로직.
     * 현재위치 아이콘 클릭시 실행.
     */
    fun setCurrentLocation() {
        ioScope {
            /**
             * 갱신하는 작업이 있을경우에는 일단은 취소.
             */
            if (::renewJob.isInitialized && renewJob.isActive) {
                renewJob.cancel()
            }
            viewStateChanged(MapViewState.ShowProgress)
            /**
             * 현재 위치를 가져옴.
             */
            when (val result = gpsTracker.getLocation()) {
                is Result.Success -> {
                    result.data.addOnCompleteListener { task ->
                        val location = task.result

                        /**
                         * 현재 위치로 이동.
                         */
                        val resultMapPoint =
                            MapPoint.mapPointWithGeoCoord(location.latitude, location.longitude)
                        viewStateChanged(MapViewState.SetCurrentLocation(resultMapPoint))
                        viewStateChanged(MapViewState.HideProgress)
                        viewStateChanged(MapViewState.SetZoomLevel(4))

                        /**
                         * 갱신하는 작업을 다시 시작.
                         */
                        ioScope {
                            renewCurrentLocation()
                        }
                    }
                }

                is Result.Error -> {
                    /**
                     * 현재 위치를 가져오지 못할 경우.
                     */
                    viewStateChanged(MapViewState.Error(result.exception.message.toString()))
                    viewStateChanged(MapViewState.HideProgress)
                }
            }
        }
    }

    /**
     * 현재위치를 매초 갱신하며 범위내 범죄자들이 있는지 배너를 보여주는 로직.
     */
    private suspend fun renewCurrentLocation() {

        /**
         * 현재위치 가져오는 작업 시작.
         */
        renewJob = ioScope {
            while (true) {
                /**
                 * 2초마다 갱신
                 */
                delay(RENEW_CURRENT_LOCATION_INTERVAL)

                /**
                 * 현재 위치를 가져옴.
                 */
                when (val result = gpsTracker.getLocation()) {
                    is Result.Success -> {
                        result.data.addOnCompleteListener { task ->
                            val location = task.result
                            val resultMapPoint =
                                MapPoint.mapPointWithGeoCoord(location.latitude, location.longitude)

                            ioScope {
                                /**
                                 * 로컬 DB 에 저장되어 있는 범죄자 리스트를 가져옴.
                                 */
                                when (val criminalResult = criminalRepository.getLocalCriminals()) {
                                    is Result.Success -> {

                                        /**
                                         * 현재위치에서 설정한 반경내에 범죄자들을 리스트업.
                                         */
                                        val toAroundList = criminalResult.data.filter {
                                            DistanceManager.getDistance(
                                                lat1 = location.latitude,
                                                lon1 = location.longitude,
                                                lat2 = it.latitude,
                                                lon2 = it.longitude
                                            ) <= settingRoundCriminal
                                        }

                                        /**
                                         * 리스트업한 범죄자가 있는경우 배너실행.
                                         */
                                        if (toAroundList.isNotEmpty()) {
                                            viewStateChanged(
                                                MapViewState.AroundCriminals(
                                                    toAroundList
                                                )
                                            )
                                        }

                                        /**
                                         * 현재 위치 갱신.
                                         */
                                        viewStateChanged(
                                            MapViewState.RenewCurrentLocation(
                                                resultMapPoint
                                            )
                                        )
                                    }

                                    is Result.Error -> {
                                        /**
                                         * 로컬DB에서 범죄자 리스트를 가져오지 못한 경우.
                                         */
                                        viewStateChanged(MapViewState.Error("범죄자 데이터 호출에 실패하였습니다."))
                                    }
                                }
                            }
                        }
                    }
                    is Result.Error -> {
                        /**
                         * 현재 위치를 가져오지 못한 경우.
                         */
                        viewStateChanged(MapViewState.Error(result.exception.message.toString()))
                    }
                }
            }
        }

        /**
         * 위의 작업을 실행한다는 의미
         */
        renewJob.join()
    }

    /**
     * 주변에 범죄자가 몇명있는지 화면으로 이동.
     */
    fun aroundCriminalList() {
        viewStateChanged(MapViewState.RouteAroundCriminalList)
    }

    /**
     * 회원탈퇴.
     */
    fun withdraw() {
        ioScope {
            /**
             * 파이어베이스에서 현재 로그인되어있는 회원을 탈퇴 로직.
             */
            firebaseRepository.delete()?.addOnCompleteListener {
                if (it.isSuccessful) {
                    viewStateChanged(MapViewState.WithdrawUser)
                } else {
                    viewStateChanged(MapViewState.Error("회원탈퇴를 실패하였습니다."))
                }
            }
        }
    }

    /**
     * 카카오맵에 저장한 범죄자들을 보여주는 로직.
     */
    fun showCriminals() {
        viewStateChanged(MapViewState.ShowProgress)
        ioScope {
            /**
             * 로컬DB 에 저장한 범죄자들을 가져옴.
             */
            when (val result = criminalRepository.getLocalCriminals()) {
                is Result.Success -> {
                    /**
                     * 가져온 범죄자들을 아이콘화하여 리스트업.
                     */
                    val toMapPOIItem = result.data.map { it.toCriminalItem().toMapPOIItem() }
                    viewStateChanged(MapViewState.GetCriminalItems(toMapPOIItem.toTypedArray()))
                }

                is Result.Error -> {
                    /**
                     * 범죄자 데이터를 가져오지 못한 경우.
                     */
                    viewStateChanged(MapViewState.Error("범죄자 데이터 호출에 실패하였습니다."))
                }
            }
            viewStateChanged(MapViewState.HideProgress)
        }
    }

    /**
     * 로그아웃
     */
    fun logout() {
        ioScope {
            /**
             * 파이어베이스 로그아웃 로직.
             */
            if (firebaseRepository.logout()) {
                viewStateChanged(MapViewState.LogoutUser)
            } else {
                viewStateChanged(MapViewState.Error("로그아웃이 실패하였습니다."))
            }
        }
    }

    /**
     * 112 전화.
     */
    fun callPolice() {
        viewStateChanged(MapViewState.CallPolice)
    }

    /**
     * 범죄자 아이콘을 선택하였을때의 로직.
     * 범죄자 이름을 파라메터로 받음.
     */
    fun getSelectPOIItemInfo(itemName: String) {
        ioScope {
            /**
             * 파라메터로 입력받은 범죄자의 정보를 로컬 DB 에서 가져옴.
             */
            when (val criminalResult = criminalRepository.getCriminalEntity(itemName)) {
                is Result.Success -> {
                    ioScope {
                        /**
                         * 현재 위치를 가져옴.
                         */
                        when (val locationResult = gpsTracker.getLocation()) {
                            is Result.Success -> {

                                /**
                                 * 가져온 현재 위치와 로컬DB에서 검색한 범죄자의 거리를 계산하여
                                 * 거리와 검색한 범죄자를 보여줌.
                                 */
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

                            /**
                             * 현재 위치를 가져오지 못한 경우.
                             */
                            is Result.Error -> {
                                viewStateChanged(MapViewState.Error(locationResult.exception.message.toString()))
                            }
                        }
                    }
                }

                /**
                 * 검색한 범죄자를 가져오지 못한 경우.
                 */
                is Result.Error -> {
                    viewStateChanged(MapViewState.Error(criminalResult.exception.message.toString()))
                }
            }
        }
    }

    /**
     * 사람 아이콘 클릭시 팝업을 보여줌.
     */
    fun showUserPopupMenu() {
        viewStateChanged(MapViewState.ShowUserPopupMenu)
    }

    companion object {

        /**
         * 현재 위치 갱신 인터벌 시간.
         */
        private const val RENEW_CURRENT_LOCATION_INTERVAL = 2000L

    }
}