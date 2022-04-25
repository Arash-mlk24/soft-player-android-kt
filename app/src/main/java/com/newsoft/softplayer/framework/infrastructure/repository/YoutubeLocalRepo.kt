package com.newsoft.softplayer.framework.infrastructure.repository

import com.newsoft.softplayer.core.AppError
import com.newsoft.softplayer.core.repository.IYoutubeRepo
import com.newsoft.softplayer.framework.infrastructure.dao.YoutubeDao
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.framework.network.dto.youtube.YoutubeItemDto

class YoutubeLocalRepo(private val youtubeDao: YoutubeDao) : IYoutubeRepo {

    override fun getAll(): List<YoutubeVideo> {
        return youtubeDao.getVideos() ?: throw AppError("NULL_POINTER_EXCEPTION")
    }

    override fun save(item: YoutubeVideo): String {
        val video = YoutubeVideo(item.url, item.title, item.channelName, item.imageSource);
        video.id = item.id
        youtubeDao.insertVideo(video);
        return item.id;
    }
}