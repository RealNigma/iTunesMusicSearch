package com.realnigma.itunesmusicsearch

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface iTunesAPI {

    @GET("search")
    fun getApiData(@Query("term") query : String, @Query("entity") entity : String = "music") : Call<Single<MusicResponse>>

}