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

    private fun loadCriminals() {
        ioScope {
            when (val result = criminalRepository.getRemoteCriminals()) {
                is Result.Success -> {
                    getLocationList(result.data) {
                        val toZipList = result.data.zip(it).map { zip ->
                            CriminalEntity(
                                name = zip.first.name,
                                address = zip.first.addressReal,
                                latitude = zip.second.latitude.toDouble(),
                                longitude = zip.second.longitude.toDouble()
                            )
                        }

                        ioScope {
                            if (criminalRepository.registerCriminalEntity(toZipList)) {
                                viewStateChanged(SplashViewState.RouteHome)
                            } else {
                                viewStateChanged(SplashViewState.Error("저장을 실패하였습니다. 다시 시도해 주세요."))
                            }
                        }
                    }
                }

                is Result.Error -> {
                    viewStateChanged(SplashViewState.Error("범죄자 데이터를 가지고 올 수 없습니다. 다시 시도해 주세요."))
                }
            }
            viewStateChanged(MapViewState.HideProgress)
        }
    }


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