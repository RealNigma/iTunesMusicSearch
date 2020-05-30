package com.realnigma.itunesmusicsearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.realnigma.itunesmusicsearch.viewmodel.MusicViewModel
import com.realnigma.itunesmusicsearch.R
import kotlinx.android.synthetic.main.activity_main.*

class AlbumActivity : AppCompatActivity() {

    private lateinit var musicViewModel: MusicViewModel
    private var albumAdapter = AlbumAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initRecyclerView()

        albumRecyclerViewOnScrollListener()
        floatingButtonOnClickListener()

    }

    private fun floatingButtonOnClickListener() {
        searchFloatingActionButton.setOnClickListener {

            val searchMenuItem = findViewById<SearchView>(R.id.searchView)
            searchMenuItem.onActionViewExpanded()
            searchFloatingActionButton.hide()

        }
    }

    private fun albumRecyclerViewOnScrollListener() {
        albumRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    searchFloatingActionButton.show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy != 0) {
                    if (searchFloatingActionButton.isShown) {
                        searchFloatingActionButton.hide()
                    }
                }
            }

        })
    }

    private fun initRecyclerView() {
        albumRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = albumAdapter
        }
    }

    private fun initViewModel() {
        musicViewModel = ViewModelProvider(this).get(MusicViewModel::class.java)

        musicViewModel.albumResult.observe(this, Observer { albums ->
            albums?.let { albumAdapter.updateAlbums(it) }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

            val menuItem = menu?.findItem(R.id.searchView)
            val searchMenuItem = menuItem?.actionView

            if (searchMenuItem is SearchView) {
                searchMenuItem.queryHint = getString(R.string.find_album)
                searchMenuItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        musicViewModel.searchAlbum(query)
                        searchMenuItem.onActionViewCollapsed()
                        searchFloatingActionButton.show()
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
