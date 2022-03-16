package com.example.crimenotification.data.source.local

import com.example.crimenotification.room.entity.CriminalEntity
import com.example.crimenotification.util.Result

interface CriminalLocalDataSource {

    suspend fun getLocalCriminals(): Result<List<CriminalEntity>>

    suspend fun registerCriminalEntity(entityList: List<CriminalEntity>): Boolean
}