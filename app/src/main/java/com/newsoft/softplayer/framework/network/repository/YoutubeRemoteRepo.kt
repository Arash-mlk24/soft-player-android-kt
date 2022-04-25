package com.newsoft.softplayer.framework.network.repository

import com.newsoft.softplayer.core.AppError
import com.newsoft.softplayer.core.repository.IYoutubeRepo
import com.newsoft.softplayer.framework.common.mappers.YoutubeSearchItemMapper
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.framework.network.service.YoutubeApi

class YoutubeRemoteRepo(private val youtubeApi: YoutubeApi) : IYoutubeRepo {

    override fun getAll(): List<YoutubeVideo>? {

        val executed = youtubeApi.search().execute();
        if (!executed.isSuccessful)
            return null;

        val body = executed.body()
        val list = ArrayList<YoutubeVideo>()

        for (item in body?.items ?: throw AppError("NULL_POINTER_EXCEPTION")) {
            list.add(YoutubeSearchItemMapper().toTarget(item))
        }

        return list

    }

    override fun save(item: YoutubeVideo): String {
        TODO("Not yet implemented")
    }

}