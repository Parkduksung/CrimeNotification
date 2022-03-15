package com.example.crimenotification.data.source.remote

import com.example.crimenotification.network.api.KakaoApi
import com.example.crimenotification.network.response.KakaoSearchResponse
import com.example.crimenotification.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class KakaoRemoteDataSourceImpl @Inject constructor(private val kakaoApi: KakaoApi) :
    KakaoRemoteDataSource {

    override fun getSearchList(
        location: String,
        callback: (result: Result<KakaoSearchResponse>) -> Unit
    ) {
        kakaoApi.search(query = location).enqueue(object : Callback<KakaoSearchResponse> {
            override fun onResponse(
                call: Call<KakaoSearchResponse>,
                response: Response<KakaoSearchResponse>
            ) {
                response.body()?.let { callback(Result.Success(it)) }
            }

            override fun onFailure(call: Call<KakaoSearchResponse>, t: Throwable) {
                callback(Result.Error(Exception(t)))
            }
        })
    }
}