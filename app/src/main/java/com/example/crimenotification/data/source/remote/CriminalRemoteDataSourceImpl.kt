package com.example.crimenotification.data.source.remote

import com.example.crimenotification.network.response.CriminalResponse
import com.example.crimenotification.network.api.SheetApi
import com.example.crimenotification.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CriminalRemoteDataSourceImpl @Inject constructor(private val sheetApi: SheetApi) :
    CriminalRemoteDataSource {
    override suspend fun getCriminals(): Result<List<CriminalResponse>> = withContext(Dispatchers.IO) {
        try {
            val result = sheetApi.getSheetCriminals().execute().body()!!
            return@withContext Result.Success(result)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}