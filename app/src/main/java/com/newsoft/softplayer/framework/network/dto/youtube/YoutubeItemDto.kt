package com.newsoft.softplayer.framework.network.dto.youtube

data class YoutubeItemDto(
    val kind: String,
    val etag: String,
    val id: YoutubeIdDto,
    val snippet: YoutubeSnippetDto,
)
