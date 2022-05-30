package com.example.version_java.data.repo;

import kotlin.jvm.functions.Function1;

public interface KakaoRepository {

    void getSearchList(String location, Function1 callback);
}


//    fun getSearchList(
//            location: String,
//            callback: (result: Result<KakaoSearchResponse>) -> Unit
//        )