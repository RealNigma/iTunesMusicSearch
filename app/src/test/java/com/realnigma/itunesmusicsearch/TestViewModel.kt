package com.realnigma.itunesmusicsearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.realnigma.itunesmusicsearch.network.*
import com.realnigma.itunesmusicsearch.viewmodel.MusicViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class TestViewModel {

    @get: Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: MusicRepository

    @InjectMocks
    lateinit var viewModel: MusicViewModel

    private var fakeAlbumData: Single<AlbumResponse>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @Test
    fun testAlbumSearch() {
        val eminemAlbum = AlbumResult(
            1,
            "Eminem",
            "Recovery",
            "https://is3-ssl.mzstatic.com/image/thumb/Music128/v4/f7/59/98/f75998c1-8562-8e0c-c2ef-e4e4b58af13d/source/100x100bb.jpg",
            "Hip-hop",
            20,
            Calendar.getInstance().time
        )
        val littleBigAlbum = AlbumResult(
            2,
            "Little Big",
            "Antipositive",
            "https://is3-ssl.mzstatic.com/image/thumb/Music128/v4/be/94/31/be943176-e4d4-caac-f0a8-2150f367911a/source/100x100bb.jpg",
            "Pop",
            10,
            Calendar.getInstance().time
        )
        val albumList = arrayListOf(eminemAlbum, littleBigAlbum)
        val albumResponse =
            AlbumResponse(2, albumList)
        fakeAlbumData = Single.just(albumResponse)

        `when`(repository.searchAlbum("kotlin")).thenReturn(fakeAlbumData)

        viewModel.searchAlbum("kotlin")


        assertEquals(2, viewModel.albumResult.value?.size)
        assertEquals(false, viewModel.hasError)
    }

    private var fakeSongData : Single<SongResponse>? = null

    @Test
    fun testSongLookup() {

        val hypnoDancerSong = SongResult(
            "Hypnodancer",
            1,
            500,
            "http://link.com",
            "Little Big",
            "Hypnodancer",
            "Pop",
            "USA",
            Calendar.getInstance().time,
            "Copyright"
                                        )
        val songList = arrayListOf(hypnoDancerSong)
        val songResponse = SongResponse(1, songList)
        fakeSongData = Single.just(songResponse)
        `when`(repository.lookupAllTracksFromAlbum(1)).thenReturn(fakeSongData)

        viewModel.lookupAllTracksFromAlbum(1)

        assertEquals(1, viewModel.songResult.value?.size)
        assertEquals(false, viewModel.hasError)

    }

}