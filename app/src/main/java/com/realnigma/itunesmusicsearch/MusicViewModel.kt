package com.realnigma.itunesmusicsearch

import javax.inject.Inject

class MusicViewModel {

    @Inject lateinit var repository: MusicRepository

    init {
        DaggerMusicAPIComponent.create().inject(this)
    }


    var albumResult : List<AlbumResult>? = null

    fun searchAlbum(query : String) {
        repository.searchAlbum(query)
        albumResult = repository.albumResult
    }
}