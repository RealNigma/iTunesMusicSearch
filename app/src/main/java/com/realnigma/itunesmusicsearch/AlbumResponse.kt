package com.realnigma.itunesmusicsearch

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("result") val resultsCount : Int,
    @SerializedName("results") val result : List<AlbumResult>)


