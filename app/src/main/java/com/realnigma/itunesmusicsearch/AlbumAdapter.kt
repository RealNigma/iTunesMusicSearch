package com.realnigma.itunesmusicsearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.itunesmusicsearch.network.AlbumResult
import com.realnigma.itunesmusicsearch.network.ImageLoader
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumAdapter:
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var albums = ArrayList<AlbumResult>()

    fun updateAlbums(albums: List<AlbumResult>) {
        this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) = AlbumViewHolder(
       LayoutInflater.from(parent.context).inflate(
           R.layout.album_item, parent, false))

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val albumCard = view.albumCard

        private val albumName = view.albumName
        private val artistName = view.artistName
        private val albumImage = view.albumImage
        private val genreName = view.genreName
        private val trackCount = view.trackNumber

        fun bind(album : AlbumResult) {
            albumName.text = album.albumName
            artistName.text = album.artistName
            genreName.text = album.primaryGenreName
            trackCount.text = album.trackCount.toString() + " tracks"
            ImageLoader.loadImage(album.artworkUrl100, albumImage)
            albumCard.setOnClickListener { onClick(it, album)}
        }

        private fun onClick(view: View, album : AlbumResult) {
            val context = view.context
            val intent = Intent(context, SongActivity::class.java)
            intent.putExtra("collectionId", album.collectionId)
            context.startActivity(intent)
        }

    }


}