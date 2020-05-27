package com.realnigma.itunesmusicsearch.network

import io.reactivex.Single
import javax.inject.Inject

class MusicRepository @Inject constructor(private val api: ITunesAPI) {

    fun searchAlbum(query: String?) : Single<AlbumResponse> {
        return api.searchAlbum(query)
    }

    fun lookupAllTracksFromAlbum(id : Int?) : Single<SongResponse> {
        return  api.lookupAllTracksFromAlbum(id)
    }

}