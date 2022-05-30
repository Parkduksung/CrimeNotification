package com.example.version_java.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.version_java.room.dao.CriminalDao;
import com.example.version_java.room.entity.CriminalEntity;

@Database(entities = {CriminalEntity.class}, version = 2)
public abstract class CriminalDatabase extends RoomDatabase {
    public abstract CriminalDao criminalDao();

    private static volatile CriminalDatabase INSTANCE;

    static CriminalDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CriminalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CriminalDatabase.class, "criminal_table")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}