package com.example.cryptocurrency.data;

import com.example.cryptocurrency.data.model.CrptRespon
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query

interface AndroidService {
    @GET("top/totalvolfull") // ?limit=30&tsym=USD
    fun getAndroid(
        @Query("limit") courseData1: String = "30",
        @Query("tsym") courseData2: String = "USD"
    ) : Call<CrptRespon?>?
}
