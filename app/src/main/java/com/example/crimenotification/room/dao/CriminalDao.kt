package com.example.crimenotification.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crimenotification.room.entity.CriminalEntity

@Dao
interface CriminalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerCriminalEntity(criminalList: CriminalEntity): Long

    @Query("SELECT * FROM criminal_table")
    fun getAll(): List<CriminalEntity>

}