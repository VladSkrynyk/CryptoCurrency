package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.network.CryptoApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoRepository {

    private val apiService = CryptoApiClient.apiService

    fun fetchCryptoPrices(symbols: String, currencies: String) {
        val call = apiService.getCryptoPrices(symbols, currencies)

        call.enqueue(object : Callback<Map<String, Map<String, Double>>> {
            override fun onResponse(
                call: Call<Map<String, Map<String, Double>>>,
                response: Response<Map<String, Map<String, Double>>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        // Log cryptocurrency prices
                        for ((crypto, rates) in data) {
                            Log.d("CryptoRepository", "Crypto: $crypto, Rates: $rates")
                        }
                    }
                } else {
                    Log.e("CryptoRepository", "Failed to fetch data: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Map<String, Map<String, Double>>>, t: Throwable) {
                Log.e("CryptoRepository", "Error fetching data", t)
            }
        })
    }
}
