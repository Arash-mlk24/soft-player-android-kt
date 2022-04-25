package com.newsoft.softplayer.framework.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.newsoft.softplayer.core.contract.YoutubeAggregator
import com.newsoft.softplayer.core.aggregator.IYoutubeAggregator
import com.newsoft.softplayer.framework.infrastructure.dao.YoutubeDao
import com.newsoft.softplayer.framework.network.service.YoutubeApi
import dagger.hilt.android.components.ActivityComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, baseURL: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideYoutubeApi(retrofit: Retrofit): YoutubeApi = retrofit.create(YoutubeApi::class.java)

    @Provides
    fun provideYoutubeAggregator(
        youtubeDao: YoutubeDao,
        youtubeApi: YoutubeApi
    ): IYoutubeAggregator = YoutubeAggregator(
        youtubeDao,
        youtubeApi
    )

}