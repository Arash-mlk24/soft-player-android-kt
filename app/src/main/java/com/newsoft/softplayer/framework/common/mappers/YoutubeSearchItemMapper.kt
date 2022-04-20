package com.newsoft.softplayer.framework.common.mappers

import com.newsoft.softplayer.framework.common.IMapper
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.framework.network.dto.youtube.YoutubeItemDto

class YoutubeSearchItemMapper : IMapper<YoutubeItemDto, YoutubeVideo> {

    override fun toSource(target: YoutubeVideo): YoutubeItemDto {
        TODO("Not yet implemented")
    }

    override fun toTarget(source: YoutubeItemDto): YoutubeVideo {
        return YoutubeVideo(
            source.id.videoId,
            source.snippet.title,
            source.snippet.channelTitle,
            source.snippet.thumbnails.high.url
        )
    }

}