package com.newsoft.softplayer.core.aggregator

import com.newsoft.softplayer.core.repository.IYoutubeRepo

interface IYoutubeAggregator {
   fun getLocalRepo() : IYoutubeRepo;
   fun getRemoteRepo() : IYoutubeRepo;
}