package com.newsoft.softplayer.framework.infrastructure.dbcontext

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newsoft.softplayer.framework.infrastructure.repository.MusicDao
import com.newsoft.softplayer.framework.infrastructure.entity.Music

@Database(entities = [Music::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun musicDao(): MusicDao

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
//                    .addCallback(WordDatabaseCallback(scope))
                    .build()
            }
            return INSTANCE as AppDatabase

        }
    }

//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    populateDatabase(database.musicDao())
//                }
//            }
//        }
////
//        suspend fun populateDatabase(musicDao: MusicDao) {
//            // Delete all content here.
////            musicDao.deleteAll()
//
//            // Add sample words.
//            var music = Music(0 , "first music", "music 1 lyrics")
//            musicDao.insertMusic(music)
//            music = Music(1, "second music", "music 2 lyrics")
//            musicDao.insertMusic(music)
//
//        }
//    }

}