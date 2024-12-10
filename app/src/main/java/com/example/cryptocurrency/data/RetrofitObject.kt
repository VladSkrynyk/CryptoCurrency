package com.example.cryptocurrency.data

import android.util.Log
import com.example.cryptocurrency.data.model.CrptRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    // https://min-api.cryptocompare.com/data/top/totalvolfull?limit=30&tsym=USD

    private val BASE_URL = "https://min-api.cryptocompare.com/data/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service = retrofit.create(AndroidService::class.java)

    fun getAndroid( responseCallback: (CrptRespon?) -> Unit) {
        val call : Call<CrptRespon?>? = service.getAndroid()

        call?.enqueue(object : Callback<CrptRespon?> {
            override fun onResponse(p0: Call<CrptRespon?>, p1: Response<CrptRespon?>) {
                Log.d(TAG, "onResponse: $p1")


                responseCallback(p1.body())
            }

            override fun onFailure(p0: Call<CrptRespon?>, p1: Throwable) {
                Log.d(TAG, "onFailure: $p1")
            }

        })
    }



    val TAG = "XXX"
}