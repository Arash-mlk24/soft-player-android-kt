package com.newsoft.softplayer.framework.network.service

import com.newsoft.softplayer.framework.common.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MusicApi {

//    @Headers("X-RapidAPI-Host': 'shazam.p.rapidapi.com", "X-RapidAPI-Key': 'ed63b96f9amsh4e83870cf2ccc2cp1dcdbejsncee51ec11d22")
//    @GET("search?term={term}&locale=en-US&offset=0&limit={limit}")
//    fun searchMusic(@Path("term") term: String, @Path("limit") limit: Number) : Call<ShazamSearchServiceResult>

    companion object {

        private var musicApi: MusicApi? = null

        fun getInstance() : MusicApi {
            if (musicApi == null) {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL_SHAZAM)
                    .build()
                musicApi = retrofit.create(MusicApi::class.java)
            }
            return musicApi!!
        }

    }
}