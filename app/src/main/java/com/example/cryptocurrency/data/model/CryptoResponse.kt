package com.example.myapplication.model

// Response structure as a map of cryptocurrency to a map of currencies and their values
data class CryptoResponse(
    val cryptoMap: Map<String, Map<String, Double>>
)
