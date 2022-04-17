package com.newsoft.softplayer.temp.repository

import androidx.annotation.WorkerThread
import com.newsoft.softplayer.data.model.dao.MusicDao
import com.newsoft.softplayer.data.model.entity.Music
import kotlinx.coroutines.flow.Flow

class MusicRepository(private val musicDao: MusicDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allMusics: Flow<List<Music>> = musicDao.getAlphabetizedMusics()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(music: Music) {
        musicDao.insert(music)
    }
}