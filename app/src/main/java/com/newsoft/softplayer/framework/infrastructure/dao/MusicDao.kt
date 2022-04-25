package com.newsoft.softplayer.framework.infrastructure.dao

import androidx.room.*
import com.newsoft.softplayer.framework.infrastructure.entity.Music

@Dao
interface MusicDao {

    @Query("SELECT * FROM musics ORDER BY music_name ASC")
    fun getAlphabetizedMusics(): List<Music>

    @Query("SELECT * FROM musics ORDER BY music_id DESC")
    fun getMusics(): List<Music>

    @Insert
    fun insertMusic(music: Music?)

    @Delete
    fun deleteMusic(music: Music?)

    @Update
    fun updateMusic(music: Music?)

}