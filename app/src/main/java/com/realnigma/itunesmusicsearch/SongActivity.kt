package com.realnigma.itunesmusicsearch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.itunesmusicsearch.network.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_song.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class SongActivity : AppCompatActivity() {

    private lateinit var viewModel: MusicViewModel
    private var songAdapter = SongAdapter()

    private val collectionId : Int by lazy { intent.getIntExtra("collectionId" ,0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        initViewModel()
        initRecyclerView()
        viewModel.lookupAllTracksFromAlbum(collectionId)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MusicViewModel::class.java)

        viewModel.songResult.observe(this, Observer { song ->
            song?.let {
                ImageLoader.loadImage(it[0].artworkUrl100 , albumImage)
                albumName.text = it[0].collectionName
                artistName.text = it[0].artistName
                genreName.text = getString(R.string.genre, it[0].primaryGenreName)
                releaseDate.text = getString(R.string.release_date, convertDate(it[0].releaseDate))
                countryName.text = getString(R.string.country, it[0].country)
                copyrightText.text = it[0].copyright
                songAdapter.updateSongs(it)
            }
        })
    }

    private fun initRecyclerView() {
        songRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = songAdapter
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun  convertDate(date: Date): String {
        val timeFormatter = SimpleDateFormat("dd.MM.yyyy")
        return timeFormatter.format(date)
    }


}
