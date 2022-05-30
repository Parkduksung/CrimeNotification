package com.example.version_java.data.repo;

import com.example.version_java.data.source.remote.KakaoRemoteDataSource;

import javax.inject.Inject;

import kotlin.jvm.functions.Function1;

public class KakaoRepositoryImpl implements KakaoRepository {

    private final KakaoRemoteDataSource kakaoRemoteDataSource;

    @Override
    public void getSearchList(String location, Function1 callback) {
        kakaoRemoteDataSource.getSearchList(location, callback);
    }

    @Inject
    KakaoRepositoryImpl(
            KakaoRemoteDataSource kakaoRemoteDataSource
    ) {
        this.kakaoRemoteDataSource = kakaoRemoteDataSource;
    }
}
