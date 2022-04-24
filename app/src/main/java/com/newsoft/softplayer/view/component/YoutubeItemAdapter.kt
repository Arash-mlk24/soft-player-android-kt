package com.newsoft.softplayer.view.component

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsoft.softplayer.R
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_youtube_video_item.view.*

class YoutubeItemAdapter(private val listener: YoutubeItemClickListener): RecyclerView.Adapter<YoutubeItemAdapter.YoutubeItemViewHolder>() {

    var items = ArrayList<YoutubeVideo>()

    fun setListData(data: ArrayList<YoutubeVideo>) {
        this.items = data
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_youtube_video_item, parent, false)
        return YoutubeItemViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: YoutubeItemViewHolder, position: Int) {
        holder.thumbnailImageView.setOnClickListener {
            listener.onImageClickListener(items[position])
        }
        holder.bind(items[position])
    }

    class YoutubeItemViewHolder(private val view: View, private val listener: YoutubeItemClickListener): RecyclerView.ViewHolder(view) {

        var titleTextView: TextView = view.youtube_item_title
        var channelNameTextView: TextView = view.youtube_item_channel_name
        var thumbnailImageView: ImageView = view.youtube_item_image


        fun bind(youtubeVideo: YoutubeVideo) {


            titleTextView.text = youtubeVideo.title
            channelNameTextView.text = youtubeVideo.channelName

//            thumbnailImageView.setImageURI(Uri.parse(youtubeVideo.imageSource))

            Glide
                .with(view.context) //Pass here context
                .load(youtubeVideo.imageSource)
                .into(thumbnailImageView); //pass here your imageview

            thumbnailImageView.setOnClickListener {
                listener.onImageClickListener(youtubeVideo)
            }

        }

    }

    interface YoutubeItemClickListener {
        fun onImageClickListener(youtubeVideo: YoutubeVideo)
    }

}