package com.newsoft.softplayer.core.contract

import com.newsoft.softplayer.core.repository.IYoutubeRepo
import com.newsoft.softplayer.core.aggregator.IYoutubeAggregator
import com.newsoft.softplayer.framework.infrastructure.dao.YoutubeDao
import com.newsoft.softplayer.framework.infrastructure.repository.YoutubeLocalRepo
import com.newsoft.softplayer.framework.network.repository.YoutubeRemoteRepo
import com.newsoft.softplayer.framework.network.service.YoutubeApi

class YoutubeAggregator(
    private val youtubeDao: YoutubeDao,
    private val youtubeApi: YoutubeApi,
) : IYoutubeAggregator {

    override fun getLocalRepo(): IYoutubeRepo {
        return YoutubeLocalRepo(youtubeDao)
    }

    override fun getRemoteRepo(): IYoutubeRepo {
        return YoutubeRemoteRepo(youtubeApi);
    }

}