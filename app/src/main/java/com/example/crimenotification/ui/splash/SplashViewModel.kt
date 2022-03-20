package com.example.crimenotification.ui.splash

import android.app.Application
import com.example.crimenotification.base.BaseViewModel
import com.example.crimenotification.network.response.CriminalResponse
import com.example.crimenotification.data.repo.CriminalRepository
import com.example.crimenotification.data.repo.KakaoRepository
import com.example.crimenotification.ext.ioScope
import com.example.crimenotification.network.response.Document
import com.example.crimenotification.room.entity.CriminalEntity
import com.example.crimenotification.ui.map.MapViewState
import com.example.crimenotification.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    app: Application,
    private val criminalRepository: CriminalRepository,
    private val kakaoRepository: KakaoRepository
) : BaseViewModel(app) {


    init {
        checkSaveCriminal()
    }

    /**
     * 현재 저장되어있는 범죄자 데이터가 있는지 체크
     * 있을경우, 앞의 변수 isRoute 를 true 로 바꾸게 해줌.
     * 없을경우 loadCriminals() 호출
     * 체크하는 로직이 실패할 경우 에러메세지 노출.
     */
    private fun checkSaveCriminal() {
        ioScope {
            when (val result = criminalRepository.getLocalCriminals()) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        loadCriminals()
                    } else {
                        viewStateChanged(SplashViewState.RouteHome)
                    }
                }
                is Result.Error -> {
                    viewStateChanged(SplashViewState.Error("저장된 범죄자 데이터를 가지고 올 수 없습니다. 다시 시도해 주세요."))
                }
            }
        }
    }

    /**
     * 범죄자 데이터를 로딩
     */
    private fun loadCriminals() {
        ioScope {

            /**
             * 엑셀파일 데이터를 읽어오는 로직 호출.
             */
            when (val result = criminalRepository.getRemoteCriminals()) {

                /**
                 * 엑셀파일 데이터 가져오는 결과 성공
                 */
                is Result.Success -> {


                    getLocationList(result.data) {

                        /**
                         * 범죄자 리스트에 각 좌표값 리스트를 합쳐 하나의 리스트로 바꿈.
                         */
                        val toZipList = result.data.zip(it).map { zip ->
                            CriminalEntity(
                                name = zip.first.name,
                                address = zip.first.addressReal,
                                latitude = zip.second.latitude.toDouble(),
                                longitude = zip.second.longitude.toDouble()
                            )
                        }

                        ioScope {
                            /**
                             * 하나로 합친 리스트를 저장이 잘 됬는지 체크여부.
                             */
                            if (criminalRepository.registerCriminalEntity(toZipList)) {
                                viewStateChanged(SplashViewState.RouteHome)
                            } else {
                                viewStateChanged(SplashViewState.Error("저장을 실패하였습니다. 다시 시도해 주세요."))
                            }
                        }
                    }
                }

                /**
                 * 엑셀파일 데이터 가져오는 결과 실패
                 * 실패 에러메세지 호출.
                 */
                is Result.Error -> {
                    viewStateChanged(SplashViewState.Error("범죄자 데이터를 가지고 올 수 없습니다. 다시 시도해 주세요."))
                }
            }
            viewStateChanged(MapViewState.HideProgress)
        }
    }


    /**
     * 엑셀데이터의 주소의 좌표값을 가져와 리스트로 만드는 로직
     * KakaoApi 를 통해 좌표값을 가져와 리스트업.
     */
    private fun getLocationList(list: List<CriminalResponse>, callback: (List<Document>) -> Unit) {
        var count = 1
        val documentList = mutableListOf<Document>()
        list.forEach {
            kakaoRepository.getSearchList(it.addressReal) { searchResult ->
                when (searchResult) {
                    is Result.Success -> {
                        count++
                        if (searchResult.data.documents.isNotEmpty()) {
                            documentList.add(searchResult.data.documents[0])
                        }
                        if (count == list.size) {
                            callback(documentList)
                        }
                    }
                    is Result.Error -> {
                        count++
                    }
                }
            }
        }
    }
}