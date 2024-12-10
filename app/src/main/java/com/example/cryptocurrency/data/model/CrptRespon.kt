package com.example.cryptocurrency.data.model


import com.google.gson.annotations.SerializedName

data class CrptRespon(
    @SerializedName("Data")
    val `data`: List<Data>?,
    @SerializedName("HasWarning")
    val hasWarning: Boolean?,
    @SerializedName("Message")
    val message: String?,
    @SerializedName("MetaData")
    val metaData: MetaData?,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit?,
    @SerializedName("SponsoredData")
    val sponsoredData: List<Any?>?,
    @SerializedName("Type")
    val type: Int?
)