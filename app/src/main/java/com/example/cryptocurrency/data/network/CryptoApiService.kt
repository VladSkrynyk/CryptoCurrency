package com.example.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {
    // Define the endpoint for fetching cryptocurrency prices
    @GET("data/pricemulti")
    fun getCryptoPrices(
        @Query("fsyms") symbols: String, // Comma-separated cryptocurrency symbols, e.g., "BTC,ETH"
        @Query("tsyms") currencies: String // Comma-separated fiat currencies, e.g., "USD,EUR"
    ): Call<Map<String, Map<String, Double>>>
}
