package com.newsoft.softplayer.framework.infrastructure.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "youtube_videos")
class YoutubeVideo(

    val url: String,
    val title: String,
    val channelName: String,
    val imageSource: String,

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "music_id")
    var id: Int = 0

}