package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoApiClient {
    private const val BASE_URL = "https://min-api.cryptocompare.com/"
    private const val API_KEY = "bf5f9d9d5b01a3696364e155582082f3b69f34cc4cddab87425e1b61442e4e97" // Replace with your API key

    // Create a Retrofit instance
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Provide an implementation of the CryptoApiService
    val apiService: CryptoApiService = retrofit.create(CryptoApiService::class.java)
}
