package com.example.crimenotification.data.repo

import com.example.crimenotification.network.response.KakaoSearchResponse
import com.example.crimenotification.util.Result

interface KakaoRepository {

    fun getSearchList(
        location: String,
        callback: (result: Result<KakaoSearchResponse>) -> Unit
    )
}