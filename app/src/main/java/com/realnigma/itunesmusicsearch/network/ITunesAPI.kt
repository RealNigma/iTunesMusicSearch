package com.realnigma.itunesmusicsearch.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesAPI {

    @GET("search")
    fun searchAlbum(
        @Query("term") query : String?,
        @Query("media") media : String? = "music",
        @Query("entity") entity : String? = "album"
    ) : Single<AlbumResponse>

    @GET("lookup")
    fun lookupAllTracksFromAlbum(
        @Query("id") collectionId: Int?,
        @Query("entity") entity: String? = "song",
        @Query("media") media: String? = "music"
    ) : Single<SongResponse>

}