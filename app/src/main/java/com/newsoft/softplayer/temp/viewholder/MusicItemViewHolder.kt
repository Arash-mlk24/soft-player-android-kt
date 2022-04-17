package com.newsoft.softplayer.temp.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newsoft.softplayer.R

class MusicItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val musicTextView: TextView = itemView.findViewById(R.id.music_text)
    fun bind(word: String){
        musicTextView.text = word
    }

}