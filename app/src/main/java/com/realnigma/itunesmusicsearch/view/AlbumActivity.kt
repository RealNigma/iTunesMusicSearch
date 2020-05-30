package com.realnigma.itunesmusicsearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.realnigma.itunesmusicsearch.viewmodel.MusicViewModel
import com.realnigma.itunesmusicsearch.R
import kotlinx.android.synthetic.main.activity_main.*

class AlbumActivity : AppCompatActivity() {

    private lateinit var viewModel: MusicViewModel
    private var albumAdapter =
        AlbumAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        viewModel.searchAlbum("Guns N Roses")
        initRecyclerView()

    }

    private fun initRecyclerView() {
        albumRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = albumAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MusicViewModel::class.java)

        viewModel.albumResult.observe(this, Observer { albums ->
            albums?.let { albumAdapter.updateAlbums(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

            val menuItem = menu?.findItem(R.id.search)
            val searchMenuItem = menuItem?.actionView

            if (searchMenuItem is SearchView) {
                searchMenuItem.queryHint = getString(R.string.find_album)
                // searchMenuItem.onActionViewExpanded()
                searchMenuItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        viewModel.searchAlbum(query)
                        menuItem.collapseActionView()
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }
            return true
    }




}
