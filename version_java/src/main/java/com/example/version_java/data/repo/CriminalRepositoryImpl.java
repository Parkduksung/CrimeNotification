package com.example.version_java.data.repo;

import androidx.annotation.NonNull;

import com.example.version_java.data.source.local.CrimianlLocalDataSource;
import com.example.version_java.data.source.remote.CriminalRemoteDataSource;
import com.example.version_java.room.entity.CriminalEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function1;

public class CriminalRepositoryImpl implements CriminalRepository {

    private final CriminalRemoteDataSource criminalRemoteDataSource;

    private final CrimianlLocalDataSource crimianlLocalDataSource;

    @Override
    public void getRemoteCriminals(@NonNull Function1 onSuccess, @NonNull Function1 onFailure) {
        criminalRemoteDataSource.getRemoteCriminals(onSuccess, onFailure);
    }

    @Override
    public void getLocalCriminals(@NonNull Function1 onSuccess, @NonNull Function1 onFailure) {
        crimianlLocalDataSource.getLocalCriminals(onSuccess, onFailure);
    }


    @Override
    public void registerCriminalEntity(ArrayList<CriminalEntity> entityList, @NonNull Function1 result) {
        crimianlLocalDataSource.registerCriminalEntity(entityList, result);
    }

    @Override
    public void getCriminalEntity(@NonNull String name, @NonNull Function1 onSuccess, @NonNull Function1 onFailure) {
        crimianlLocalDataSource.getCriminalEntity(name, onSuccess, onFailure);
    }

    @Inject
    CriminalRepositoryImpl(
            CriminalRemoteDataSource criminalRemoteDataSource,
            CrimianlLocalDataSource crimianlLocalDataSource
    ) {
        this.criminalRemoteDataSource = criminalRemoteDataSource;
        this.crimianlLocalDataSource = crimianlLocalDataSource;
    }
}
