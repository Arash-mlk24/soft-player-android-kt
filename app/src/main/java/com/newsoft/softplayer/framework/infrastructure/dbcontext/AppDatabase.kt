package com.newsoft.softplayer.framework.infrastructure.dbcontext

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newsoft.softplayer.framework.infrastructure.dao.MusicDao
import com.newsoft.softplayer.framework.infrastructure.dao.YoutubeDao
import com.newsoft.softplayer.framework.infrastructure.entity.Music
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo

@Database(entities = [Music::class, YoutubeVideo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun musicDao(): MusicDao
    abstract fun youtubeDao(): YoutubeDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase

        }
    }

}