package com.example.version_java.di;

import android.content.Context;

import androidx.room.Room;

import com.example.version_java.room.dao.CriminalDao;
import com.example.version_java.room.database.CriminalDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RoomModule {


    @Provides
    public CriminalDao provideCriminalDao(CriminalDatabase criminalDatabase) {
        return criminalDatabase.criminalDao();
    }

    @Singleton
    @Provides
    public CriminalDatabase provideCriminal(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(
                        appContext,
                        CriminalDatabase.class,
                        "criminal_table"
                ).fallbackToDestructiveMigration()
                .build();
    }

}
