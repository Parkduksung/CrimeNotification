package com.example.crimenotification.di


import com.example.crimenotification.data.repo.CriminalRepository
import com.example.crimenotification.data.repo.CriminalRepositoryImpl
import com.example.crimenotification.data.repo.KakaoRepository
import com.example.crimenotification.data.repo.KakaoRepositoryImpl
import com.example.crimenotification.data.source.remote.CriminalRemoteDataSource
import com.example.crimenotification.data.source.remote.CriminalRemoteDataSourceImpl
import com.example.crimenotification.data.source.remote.KakaoRemoteDataSource
import com.example.crimenotification.data.source.remote.KakaoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCriminalRepository(criminalRepositoryImpl: CriminalRepositoryImpl): CriminalRepository

    @Singleton
    @Binds
    abstract fun bindCriminalRemoteDataSource(criminalRemoteDataSourceImpl: CriminalRemoteDataSourceImpl): CriminalRemoteDataSource


    @Singleton
    @Binds
    abstract fun bindKakaoRepository(kakaoRepositoryImpl: KakaoRepositoryImpl): KakaoRepository

    @Singleton
    @Binds
    abstract fun bindCKakaoRemoteDataSource(kakaoRemoteDataSourceImpl: KakaoRemoteDataSourceImpl): KakaoRemoteDataSource

}

