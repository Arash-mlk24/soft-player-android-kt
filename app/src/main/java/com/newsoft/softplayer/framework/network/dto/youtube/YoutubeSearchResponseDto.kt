package com.newsoft.softplayer.framework.network.dto.youtube

data class YoutubeSearchResponseDto (
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: YoutubePageInfoDto,
    val items: List<YoutubeItemDto>,
)