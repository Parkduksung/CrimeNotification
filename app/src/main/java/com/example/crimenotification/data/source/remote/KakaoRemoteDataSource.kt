package com.example.crimenotification.data.source.remote

import com.example.crimenotification.network.response.KakaoSearchResponse
import com.example.crimenotification.util.Result

interface KakaoRemoteDataSource {

    fun getSearchList(
        location: String,
        callback: (result: Result<KakaoSearchResponse>) -> Unit
    )
}