package com.newsoft.softplayer.framework.infrastructure.dao

import androidx.room.*
import com.newsoft.softplayer.framework.infrastructure.entity.Music
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo

@Dao
interface YoutubeDao {

    @Query("SELECT * FROM musics ORDER BY music_name ASC")
    fun getAlphabetizedVideos(): List<YoutubeVideo>?

    @Query("SELECT * FROM musics ORDER BY music_id DESC")
    fun getVideos(): List<YoutubeVideo>?

    @Insert
    fun insertVideo(video: YoutubeVideo?)

    @Delete
    fun deleteVideo(video: YoutubeVideo?)

    @Update
    fun updateVideo(video: YoutubeVideo?)

}