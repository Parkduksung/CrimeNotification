package com.example.version_java.di;


import com.example.version_java.data.repo.CriminalRepository;
import com.example.version_java.data.repo.CriminalRepositoryImpl;
import com.example.version_java.data.repo.KakaoRepository;
import com.example.version_java.data.repo.KakaoRepositoryImpl;
import com.example.version_java.data.source.local.CrimianlLocalDataSource;
import com.example.version_java.data.source.local.CrimianlLocalDataSourceImpl;
import com.example.version_java.data.source.remote.CriminalRemoteDataSource;
import com.example.version_java.data.source.remote.CriminalRemoteDataSourceImpl;
import com.example.version_java.data.source.remote.KakaoRemoteDataSource;
import com.example.version_java.data.source.remote.KakaoRemoteDataSourceImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Singleton
    @Binds
    public abstract CriminalRepository bindCriminalRepository(
            CriminalRepositoryImpl criminalRepositoryImpl
    );

    @Singleton
    @Binds
    public abstract CriminalRemoteDataSource bindCriminalRemoteDataSource(
            CriminalRemoteDataSourceImpl criminalRemoteDataSourceImpl
    );

    @Singleton
    @Binds
    public abstract CrimianlLocalDataSource bindCrimianlLocalDataSource(
            CrimianlLocalDataSourceImpl crimianlLocalDataSourceImpl
    );

    @Singleton
    @Binds
    public abstract KakaoRepository bindKakaoRepository(
            KakaoRepositoryImpl kakaoRepositoryImpl
    );

    @Singleton
    @Binds
    public abstract KakaoRemoteDataSource bindKakaoRemoteDataSource(
            KakaoRemoteDataSourceImpl kakaoRemoteDataSourceImpl
    );

}
