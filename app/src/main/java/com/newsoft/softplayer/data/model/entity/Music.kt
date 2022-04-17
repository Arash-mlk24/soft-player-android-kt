package com.newsoft.softplayer.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "musics")
class Music (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "musicId")
    private val id: Int,

    private val name: String,

    private val lyrics: String,

)