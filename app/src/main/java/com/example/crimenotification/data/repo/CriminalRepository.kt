package com.example.crimenotification.data.repo

import com.example.crimenotification.network.response.CriminalResponse
import com.example.crimenotification.room.entity.CriminalEntity
import com.example.crimenotification.util.Result

interface CriminalRepository {

    suspend fun getRemoteCriminals(): Result<List<CriminalResponse>>

    suspend fun getLocalCriminals(): Result<List<CriminalEntity>>

    suspend fun registerCriminalEntity(entityList: List<CriminalEntity>): Boolean

    suspend fun getCriminalEntity(name: String): Result<CriminalEntity>

    fun  get(
        onSUccess : (List<CriminalResponse>) -> Unit
    )
}