package com.example.version_java.data.source.remote;

import com.example.version_java.network.api.KakaoApi;
import com.example.version_java.network.response.KakaoSearchResponse;

import javax.inject.Inject;

import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KakaoRemoteDataSourceImpl implements KakaoRemoteDataSource {

    private final KakaoApi kakaoApi;

    @Override
    public void getSearchList(String location, Function1 callback) {
        kakaoApi.search(location, "exact", 1, 10).enqueue(new Callback<KakaoSearchResponse>() {
            @Override
            public void onResponse(Call<KakaoSearchResponse> call, Response<KakaoSearchResponse> response) {
                if (response.body() != null) {
                    callback.invoke(response.body());
                } else {
                    callback.invoke("GetSearchList Error");
                }
            }

            @Override
            public void onFailure(Call<KakaoSearchResponse> call, Throwable t) {
                callback.invoke(t.getMessage());
            }
        });
    }

    @Inject
    KakaoRemoteDataSourceImpl(
            KakaoApi kakaoApi
    ) {
        this.kakaoApi = kakaoApi;
    }
}
