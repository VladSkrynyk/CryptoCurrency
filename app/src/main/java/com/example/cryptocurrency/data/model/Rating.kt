package com.example.cryptocurrency.data.model


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("Weiss")
    val weiss: Weiss?
)