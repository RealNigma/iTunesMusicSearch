package com.realnigma.itunesmusicsearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesAPI {

    @GET("search")
    fun searchAlbum(
        @Query("term") query : String,
        @Query("media") media : String = "music",
        @Query("entity") entity : String = "album"
    ) : Call<AlbumResponse>

    @GET("lookup")
    fun lookupAllTracksFromAlbum(
        @Query("id") collectionId: Int?,
        @Query("entity") entity: String = "song",
        @Query("media") media: String = "music"
    ) : Call<SongResponse>

}