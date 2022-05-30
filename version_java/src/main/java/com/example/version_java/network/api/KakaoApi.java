package com.example.version_java.network.api;

import com.example.version_java.network.response.KakaoSearchResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoApi {

    @Headers("Authorization: KakaoAK 1b1847cb545da9273d30d12fd848edb9")
    @GET("/v2/local/search/address.json")
    @NotNull
    Call<KakaoSearchResponse> search(@Query("query") @NotNull String query, @Query("analyze_type") @NotNull String analyze_type, @Query("page") int page, @Query("size") int size);

}
