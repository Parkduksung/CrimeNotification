package com.example.crimenotification.data.repo

import com.example.crimenotification.data.model.Criminal
import com.example.crimenotification.util.Result
interface CriminalRepository {

    suspend fun getCriminals() : Result<List<Criminal>>
}