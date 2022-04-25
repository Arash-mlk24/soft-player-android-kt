package com.newsoft.softplayer.core.repository

import com.newsoft.softplayer.core.domain.MusicEntity

interface IMusicRepo {
    fun getAll(id:String) : List<MusicEntity>?;
    fun save(item : MusicEntity) : String;
}