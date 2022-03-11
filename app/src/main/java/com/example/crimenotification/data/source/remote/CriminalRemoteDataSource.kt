package com.example.crimenotification.data.source.remote

import com.example.crimenotification.data.model.Criminal
import com.example.crimenotification.util.Result

interface CriminalRemoteDataSource {
    suspend fun getCriminals(): Result<List<Criminal>>
}