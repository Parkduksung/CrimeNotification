package com.example.crimenotification.data.source.remote

import com.example.crimenotification.network.response.CriminalResponse
import com.example.crimenotification.util.Result

interface CriminalRemoteDataSource {
    suspend fun getCriminals(): Result<List<CriminalResponse>>
}