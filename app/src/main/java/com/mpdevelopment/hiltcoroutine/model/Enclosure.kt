package com.mpdevelopment.hiltcoroutine.model

import com.google.gson.annotations.SerializedName

data class Enclosure(
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("format") val format: String
)