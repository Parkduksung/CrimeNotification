package com.example.crimenotification.di


import com.example.crimenotification.data.repo.CriminalRepository
import com.example.crimenotification.data.repo.CriminalRepositoryImpl
import com.example.crimenotification.data.source.remote.CriminalRemoteDataSource
import com.example.crimenotification.data.source.remote.CriminalRemoteDataSourceImpl
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

}

