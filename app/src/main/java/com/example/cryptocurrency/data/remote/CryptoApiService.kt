package com.example.cryptocurrency.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {
    @GET("data/pricemulti")
    suspend fun getCryptoPrices(
        @Query("fsyms") fromSymbols: String,  // Comma-separated list of cryptocurrency symbols
        @Query("tsyms") toSymbols: String     // Comma-separated list of currency symbols (e.g., "USD", "EUR")
    ): Map<String, Map<String, Double>>  // Return type is a nested map (crypto symbol -> (currency symbol -> price))
}
