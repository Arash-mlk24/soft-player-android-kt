package com.newsoft.softplayer.core.repository

import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.framework.network.dto.youtube.YoutubeItemDto

interface IYoutubeRepo {
    fun getAll() : List<YoutubeVideo>?;
    fun save(item : YoutubeVideo ) : String;
}