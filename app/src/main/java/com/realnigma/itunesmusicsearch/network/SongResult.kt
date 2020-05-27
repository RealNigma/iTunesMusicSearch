package com.realnigma.itunesmusicsearch.network

import com.google.gson.annotations.SerializedName

data class SongResult(
    @SerializedName("trackName") val trackName : String
)