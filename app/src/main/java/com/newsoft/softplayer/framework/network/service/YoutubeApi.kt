package com.newsoft.softplayer.framework.network.service

import com.newsoft.softplayer.framework.common.Constants
import com.newsoft.softplayer.framework.network.dto.youtube.YoutubeSearchResponseDto
import com.newsoft.softplayer.framework.network.utils.RetrofitBuilder
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("v3/search?part=snippet&maxResults=20&type=video&q=android&key=${Constants.YOUTUBE_API_KEY}")
//    fun search(@Query("term") term: String, @Query("maxResult") limit: Number): Call<YoutubeSearchResponseDto>
    fun search(): Call<YoutubeSearchResponseDto>

    companion object {

        private var youtubeApi: YoutubeApi? = null

        fun getInstance(): YoutubeApi {
            if (youtubeApi == null) youtubeApi =
                RetrofitBuilder.build(
                    YoutubeApi::class.java,
                    Constants.BASE_URL_YOUTUBE
                )
            return youtubeApi!!
        }

    }

}