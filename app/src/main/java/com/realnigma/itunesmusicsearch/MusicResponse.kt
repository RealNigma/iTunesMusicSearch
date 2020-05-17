package com.realnigma.itunesmusicsearch

import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("result") val resultsCount : Int,
    @SerializedName("results") val result : List<MusicResult>)


