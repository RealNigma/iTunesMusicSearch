package com.realnigma.itunesmusicsearch.network

import com.google.gson.annotations.SerializedName

data class AlbumResult(
    @SerializedName("collectionId") val collectionId : Int,
    @SerializedName("artistName") val artistName : String,
    @SerializedName("collectionName") val albumName : String,
    @SerializedName("artworkUrl100") val artworkUrl100 : String,
    @SerializedName("primaryGenreName") val primaryGenreName : String,
    @SerializedName("trackCount") val trackCount : Int
)
