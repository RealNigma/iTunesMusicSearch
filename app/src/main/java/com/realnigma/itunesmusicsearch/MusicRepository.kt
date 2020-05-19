package com.realnigma.itunesmusicsearch

import android.app.DownloadManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicRepository constructor(private val musicAPI : ITunesAPI) {

    var albumRequest : Call<AlbumResult>? = null
    var albumResult: List<AlbumResult>? = null
    var albumResultCount : Int = 0
    var error : String? = ""

    fun albumSearch(query: String) {
        val albumResponse = musicAPI.searchAlbum(query).enqueue(object: Callback<AlbumResponse> {
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