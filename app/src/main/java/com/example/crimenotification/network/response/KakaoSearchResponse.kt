package com.example.crimenotification.network.response

data class KakaoSearchResponse(
    val documents: List<Document>,
    val meta: Meta
)