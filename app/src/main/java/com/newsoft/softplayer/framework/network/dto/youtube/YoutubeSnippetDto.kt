package com.newsoft.softplayer.framework.network.dto.youtube

data class YoutubeSnippetDto(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: YoutubeThumbnailsDto,
    val channelTitle: String,
    val liveBroadcastContent: String,
    val publishTime: String
)
