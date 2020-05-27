package com.realnigma.itunesmusicsearch.network

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("resultCount") val resultsCount : Int,
    @SerializedName("results") val results : List<AlbumResult>)


