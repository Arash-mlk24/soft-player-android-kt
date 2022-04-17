package com.newsoft.softplayer.temp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsoft.softplayer.R
import com.newsoft.softplayer.temp.viewholder.MusicItemViewHolder


class MusicItemAdapter(private val musicList: Array<String>): RecyclerView.Adapter<MusicItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_music_item, parent, false)
        return MusicItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

}