package com.example.crimenotification.data.repo

import com.example.crimenotification.data.source.local.CriminalLocalDataSource
import com.example.crimenotification.data.source.remote.CriminalRemoteDataSource
import com.example.crimenotification.network.response.CriminalResponse
import com.example.crimenotification.room.entity.CriminalEntity
import com.example.crimenotification.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CriminalRepositoryImpl @Inject constructor(
    private val criminalRemoteDataSource: CriminalRemoteDataSource,
    private val criminalLocalDataSource: CriminalLocalDataSource
) : CriminalRepository {

    override suspend fun getRemoteCriminals(): Result<List<CriminalResponse>> =
        withContext(Dispatchers.IO) {
            return@withContext criminalRemoteDataSource.getCriminals()
        }

    override suspend fun getLocalCriminals(): Result<List<CriminalEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext criminalLocalDataSource.getLocalCriminals()
        }


    override suspend fun registerCriminalEntity(entityList: List<CriminalEntity>): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext criminalLocalDataSource.registerCriminalEntity(entityList)
        }

    override suspend fun getCriminalEntity(name: String): Result<CriminalEntity> =
        withContext(Dispatchers.IO) {
            return@withContext criminalLocalDataSource.getCriminalEntity(name)
        }
}