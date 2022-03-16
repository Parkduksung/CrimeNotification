package com.example.crimenotification.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.crimenotification.room.dao.CriminalDao
import com.example.crimenotification.room.entity.CriminalEntity

@Database(entities = [CriminalEntity::class], version = 2)
abstract class CriminalDatabase : RoomDatabase() {
    abstract fun criminalDao(): CriminalDao
}