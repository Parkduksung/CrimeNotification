package com.example.crimenotification.data.repo

import com.example.crimenotification.data.model.Criminal
import com.example.crimenotification.data.source.remote.CriminalRemoteDataSource
import com.example.crimenotification.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CriminalRepositoryImpl @Inject constructor(private val criminalRemoteDataSource: CriminalRemoteDataSource) :
    CriminalRepository {

    override suspend fun getCriminals(): Result<List<Criminal>> = withContext(Dispatchers.IO) {
        return@withContext criminalRemoteDataSource.getCriminals()
    }
}