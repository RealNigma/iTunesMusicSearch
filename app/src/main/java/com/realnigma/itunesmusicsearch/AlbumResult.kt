package com.realnigma.itunesmusicsearch

import com.google.gson.annotations.SerializedName

data class AlbumResult(
    @SerializedName("collectionId") val collectionId : Int,
    @SerializedName("artistName") val artistName : String,
    @SerializedName("collectionName") val albumName : String
)
