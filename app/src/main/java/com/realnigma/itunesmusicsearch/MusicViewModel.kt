package com.realnigma.itunesmusicsearch

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MusicViewModel : ViewModel() {

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