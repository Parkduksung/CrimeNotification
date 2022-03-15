package com.example.crimenotification.di

import com.example.crimenotification.network.api.KakaoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoModule {
    @Singleton
    @Provides
    fun provideKakaoApi(): KakaoApi {
        return Retrofit.Builder()
            .baseUrl(KAKAO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoApi::class.java)
    }

    private const val KAKAO_URL = "https://dapi.kakao.com/"
}
