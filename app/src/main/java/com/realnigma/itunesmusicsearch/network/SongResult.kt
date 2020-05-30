package com.realnigma.itunesmusicsearch.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class SongResult(
    @SerializedName("trackName") val trackName : String,
    @SerializedName("trackNumber") val trackNumber : Int,
    @SerializedName("trackTimeMillis") val trackTimeMillis : Long,
    @SerializedName("artworkUrl100") val artworkUrl100 : String,
    @SerializedName("artistName") val artistName : String,
    @SerializedName("collectionName") val collectionName : String,
    @SerializedName("primaryGenreName") val primaryGenreName : String,
    @SerializedName("country") val country : String,
    @SerializedName("releaseDate") val releaseDate : Date,
    @SerializedName("copyright") val copyright : String
)