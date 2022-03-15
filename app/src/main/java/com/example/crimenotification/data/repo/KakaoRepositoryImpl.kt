package com.example.crimenotification.data.repo

import com.example.crimenotification.data.source.remote.KakaoRemoteDataSource
import com.example.crimenotification.network.response.KakaoSearchResponse
import com.example.crimenotification.util.Result
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {
    override fun getSearchList(
        location: String,
        callback: (result: Result<KakaoSearchResponse>) -> Unit
    ) {
        kakaoRemoteDataSource.getSearchList(location, callback)
    }
}