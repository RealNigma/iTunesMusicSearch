package com.realnigma.itunesmusicsearch

import com.google.gson.annotations.SerializedName

data class MusicResult(
    @SerializedName("artistName") val artistName : String,
    @SerializedName("trackName") val trackName : String
)
