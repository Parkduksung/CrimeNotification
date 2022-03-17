package com.example.crimenotification.di


import com.example.crimenotification.data.repo.*
import com.example.crimenotification.data.source.local.CriminalLocalDataSource
import com.example.crimenotification.data.source.local.CriminalLocalDataSourceImpl
import com.example.crimenotification.data.source.remote.*
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
    abstract fun bindCriminalLocalDataSource(criminalLocalDataSourceImpl: CriminalLocalDataSourceImpl): CriminalLocalDataSource

    @Singleton
    @Binds
    abstract fun bindKakaoRepository(kakaoRepositoryImpl: KakaoRepositoryImpl): KakaoRepository

    @Singleton
    @Binds
    abstract fun bindCKakaoRemoteDataSource(kakaoRemoteDataSourceImpl: KakaoRemoteDataSourceImpl): KakaoRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindFirebaseRepository(firebaseRepositoryImpl: FirebaseRepositoryImpl): FirebaseRepository

    @Singleton
    @Binds
    abstract fun bindFirebaseRemoteDataSource(firebaseRemoteDataSourceImpl: FirebaseRemoteDataSourceImpl): FirebaseRemoteDataSource

}

