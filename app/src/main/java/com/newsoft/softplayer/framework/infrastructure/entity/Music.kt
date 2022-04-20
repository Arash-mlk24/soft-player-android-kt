package com.newsoft.softplayer.framework.infrastructure.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "musics")
class Music(

    @ColumnInfo(name = "music_name")
    val name: String,

    @ColumnInfo(name = "lyrics")
    val lyrics: String

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "music_id")
    var id: Int = 0

}

//    (
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "musicId")
//    private val id: Int,
//
//    @ColumnInfo(name = "name")
//    val name: String,
//
//    @ColumnInfo(name = "lyrics")
//    val lyrics: String,
//
//)