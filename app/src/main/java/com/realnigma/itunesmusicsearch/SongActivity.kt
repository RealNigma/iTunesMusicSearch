package com.realnigma.itunesmusicsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity() {

    private lateinit var viewModel: MusicViewModel

    private val collectionId : Int by lazy { intent.getIntExtra("collectionId" ,0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        initViewModel()
        viewModel.lookupAllTracksFromAlbum(collectionId)


    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MusicViewModel::class.java)

        viewModel.songResult.observe(this, Observer { song ->
            song?.let { albumName.text = it[1].trackName }
        })
    }
}
