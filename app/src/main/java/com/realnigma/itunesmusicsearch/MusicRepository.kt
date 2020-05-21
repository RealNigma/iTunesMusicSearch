package com.realnigma.itunesmusicsearch

import android.app.DownloadManager
import dagger.Provides
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MusicRepository @Inject constructor(private val musicAPI : ITunesAPI) {

    var albumResult: List<AlbumResult>? = null
    var albumResultCount : Int = 0
    var error : String? = ""

    fun searchAlbum(query: String) {
        musicAPI.searchAlbum(query).enqueue(object: Callback<AlbumResponse> {
            override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {
                response.body()?.let {
                albumResult = it.result
                    albumResultCount = it.resultsCount
                }
            }
            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                error = t.localizedMessage
            }
        }
        )
    }
}