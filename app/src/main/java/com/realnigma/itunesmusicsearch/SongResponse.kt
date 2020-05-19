package com.realnigma.itunesmusicsearch

import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("result") val resultsCount : Int,
    @SerializedName("results") val results : List<SongResult>
)