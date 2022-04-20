package com.newsoft.softplayer.framework.network.utils

import com.newsoft.softplayer.framework.common.Constants
import com.newsoft.softplayer.framework.network.service.YoutubeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.reflect.KClass

class RetrofitBuilder {

    companion object {
        fun <T : Any> build(typeClass: Class<T>, baseURL: String): T {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()
            return retrofit.create(typeClass)
        }
    }

}