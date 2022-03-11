package com.example.crimenotification.network.api

import com.example.crimenotification.data.model.Criminal
import retrofit2.Call
import retrofit2.http.GET

interface SheetApi {

    companion object {
        private const val URL = "api/v1/d1skgjsjp83q0"

    }

    @GET(URL)
    fun getSheetCriminals(): Call<List<Criminal>>

}