package com.newsoft.softplayer.application

import android.app.Application
import com.newsoft.softplayer.data.model.db.MusicDatabase
import com.newsoft.softplayer.temp.repository.MusicRepository

class ApplicationCore: Application() {

    override fun onCreate() {
        super.onCreate()

        val database by lazy { MusicDatabase.getDatabase(this) }
        val repository by lazy { MusicRepository(database.musicDao()) }
    }

}