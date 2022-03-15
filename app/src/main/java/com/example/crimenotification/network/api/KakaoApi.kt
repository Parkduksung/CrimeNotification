package com.example.crimenotification.network.api

import com.example.crimenotification.network.response.KakaoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApi {

    companion object {
        private const val REST_API_KEY = "1b1847cb545da9273d30d12fd848edb9"
        private const val SEARCH = "/v2/local/search/address.json"
    }

    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET(SEARCH)
    fun search(
        @Query("query") query: String,
        @Query("analyze_type") analyze_type: String = "exact",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Call<KakaoSearchResponse>

}