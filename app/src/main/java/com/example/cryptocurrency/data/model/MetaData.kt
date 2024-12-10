package com.example.cryptocurrency.data.model


import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("Count")
    val count: Int?
)