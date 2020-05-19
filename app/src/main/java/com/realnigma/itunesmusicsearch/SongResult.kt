package com.realnigma.itunesmusicsearch

import com.google.gson.annotations.SerializedName

data class SongResult(
    @SerializedName("trackName") val trackName : String
)