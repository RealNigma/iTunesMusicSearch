package com.realnigma.itunesmusicsearch

import android.app.DownloadManager
import android.util.Log
import androidx.annotation.WorkerThread
import dagger.Provides
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MusicRepository {

    @Inject
    lateinit var api : ITunesAPI

    init {
        DaggerMusicAPIComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    var albumResult: List<AlbumResult>? = null
    var albumResultCount : Int = 0
    var error : String? = ""


    fun searchAlbum(query: String) {



        disposable.add(api.searchAlbum(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<AlbumResponse>() {
                override fun onSuccess(t: AlbumResponse) {
                    albumResult = t.result
                    albumResultCount = t.resultsCount
                    Log.w("iTunes", "result: ${t.result}, query: $query" )
                }

                override fun onError(e: Throwable) {
                    error = e.localizedMessage
                    Log.w("iTunes", error!!)
                }

            })
        )
    }
}