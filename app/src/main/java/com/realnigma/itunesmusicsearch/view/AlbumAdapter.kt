package com.realnigma.itunesmusicsearch.view

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.itunesmusicsearch.R
import com.realnigma.itunesmusicsearch.network.AlbumResult
import com.realnigma.itunesmusicsearch.utils.ImageLoader
import kotlinx.android.synthetic.main.album_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AlbumAdapter:
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var albums = ArrayList<AlbumResult>()

    fun updateAlbums(albums: List<AlbumResult>) {
        this.albums.clear()
        this.albums.addAll(albums)
        this.albums.sortBy { it.albumName }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) =
        AlbumViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.album_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val albumCard = view.albumCard

        private val albumName = view.albumName
        private val releaseDate = view.releaseDate
        private val albumImage = view.albumImage

        fun bind(album : AlbumResult) {
            albumName.text = album.albumName
            releaseDate.text = convertDate(album.releaseDate)
            ImageLoader.loadImage(album.artworkUrl100, albumImage)
            albumCard.setOnClickListener { onClick(it, album)}
        }

        private fun onClick(view: View, album : AlbumResult) {
            val context = view.context
            val intent = Intent(context, SongActivity::class.java)
            intent.putExtra("collectionId", album.collectionId)
            context.startActivity(intent)
        }

        @SuppressLint("SimpleDateFormat")
        private fun  convertDate(date: Date): String {
            val timeFormatter = SimpleDateFormat("yyyy")
            return timeFormatter.format(date)
        }

    }


}