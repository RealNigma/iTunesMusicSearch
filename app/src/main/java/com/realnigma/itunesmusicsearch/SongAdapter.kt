package com.realnigma.itunesmusicsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.itunesmusicsearch.network.SongResult
import kotlinx.android.synthetic.main.song_item.view.*
import java.util.concurrent.TimeUnit

class SongAdapter :
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var songs = ArrayList<SongResult>()

    fun updateSongs(songs: List<SongResult>) {
        this.songs.clear()
        this.songs.addAll(songs)
        this.songs.removeAt(0)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) = SongViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.song_item, parent, false))

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songs[position])
    }


    class SongViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        private val trackName = view.trackName
        private val trackNumber = view.trackNumber
        private val trackTime = view.trackTime


        fun bind(song : SongResult) {
            trackName.text = song.trackName
            trackNumber.text = song.trackNumber.toString()
            trackTime.text = convertTime(song.trackTimeMillis)
        }

        private fun convertTime(millis : Long) : String {
            return String.format("%2d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            )
        }

    }


}