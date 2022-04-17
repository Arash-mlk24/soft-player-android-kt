package com.newsoft.softplayer.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.newsoft.softplayer.data.model.entity.Music
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(music: Music)

    @Query("SELECT * FROM musics ORDER BY name ASC")
    fun getAlphabetizedMusics(): Flow<List<Music>>

    @Query("DELETE FROM musics")
    suspend fun deleteAll()

}