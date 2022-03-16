package com.example.crimenotification.di

import android.content.Context
import androidx.room.Room
import com.example.crimenotification.room.dao.CriminalDao
import com.example.crimenotification.room.database.CriminalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideCriminalDao(criminalDatabase: CriminalDatabase): CriminalDao {
        return criminalDatabase.criminalDao()
    }

    @Singleton
    @Provides
    fun provideCriminal(@ApplicationContext appContext: Context): CriminalDatabase {
        return Room.databaseBuilder(
            appContext,
            CriminalDatabase::class.java,
            "criminal_table"
        ).fallbackToDestructiveMigration()
            .build()
    }
}