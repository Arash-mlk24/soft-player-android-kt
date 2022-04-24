package com.newsoft.softplayer.view.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newsoft.softplayer.R
import com.newsoft.softplayer.framework.infrastructure.entity.Music
import kotlinx.android.synthetic.main.fragment_music_item.view.*


class MusicItemAdapter(private val listener: MusicItemClickListener): RecyclerView.Adapter<MusicItemAdapter.MusicItemViewHolder>() {

    var items = ArrayList<Music>()

    fun setListData(data: ArrayList<Music>) {
        this.items = data
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_music_item, parent, false)
        return MusicItemViewHolder(inflater, listener)
        
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.musicText.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }

    class MusicItemViewHolder(view: View, private val listener: MusicItemClickListener): RecyclerView.ViewHolder(view) {

        var musicText: TextView = view.music_text

        fun bind(music: Music) {

            musicText.text = music.name

            musicText.setOnClickListener {
                listener.onItemClickListener(music)
            }

        }

    }

    interface MusicItemClickListener {
        fun onItemClickListener(music: Music)
    }

}