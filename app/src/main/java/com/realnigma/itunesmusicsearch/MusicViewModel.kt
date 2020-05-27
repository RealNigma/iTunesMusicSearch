package com.realnigma.itunesmusicsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.realnigma.itunesmusicsearch.dagger.DaggerMusicAPIComponent
import com.realnigma.itunesmusicsearch.network.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MusicViewModel : ViewModel() {

    @Inject
    lateinit var repository: MusicRepository
    var hasError = false

    init {
        DaggerMusicAPIComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()
    var albumResult = MutableLiveData<List<AlbumResult>>()
    var albumResultCount = MutableLiveData<Int>()
    var error : String? = ""

    fun searchAlbum(query : String?) {
        disposable.add(
            repository.searchAlbum(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AlbumResponse>() {
                    override fun onSuccess(t: AlbumResponse) {
                        albumResult.value = t.results
                        albumResultCount.value = t.resultsCount
                        //Log.w("iTunes", "result: ${t.results}, query: $query" )
                    }

                    override fun onError(e: Throwable) {
                        error = e.localizedMessage
                        hasError = true
                        //Log.w("iTunes", error)
                    }
                })
        )
    }

    var songResult = MutableLiveData<List<SongResult>>()
    var songResultCount = MutableLiveData<Int>()

    fun lookupAllTracksFromAlbum(id : Int?) {
        disposable.add(
            repository.lookupAllTracksFromAlbum(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SongResponse>() {
                    override fun onSuccess(t: SongResponse) {
                        songResult.value = t.results
                        songResultCount.value = t.resultsCount
                        Log.w("iTunes", "result: ${t.results}, id: $id" )
                    }

                    override fun onError(e: Throwable) {
                        error = e.localizedMessage
                        hasError = true
                        Log.w("iTunes", error)
                    }
                })

        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}