package com.newsoft.softplayer.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsoft.softplayer.application.ApplicationCore
import com.newsoft.softplayer.framework.common.mappers.YoutubeSearchItemMapper
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.framework.network.dto.youtube.YoutubeSearchResponseDto
import com.newsoft.softplayer.framework.network.service.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragmentViewModel(app: ApplicationCore) : AndroidViewModel(app) {

    private val data: MutableLiveData<List<YoutubeVideo>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getDataObserver (): MutableLiveData<List<YoutubeVideo>> {
        return data
    }

    fun getErrorMessageObserver(): MutableLiveData<String> {
        return errorMessage
    }

    fun setErrorMessage(errorText: String) {
        errorMessage.postValue(errorText)
    }

    fun searchTerm(term: String) {

        val response = YoutubeApi.getInstance().search()

        response.enqueue(object : Callback<YoutubeSearchResponseDto> {
            override fun onResponse(
                call: Call<YoutubeSearchResponseDto>,
                response: Response<YoutubeSearchResponseDto>
            ) {
                Log.d(TAG, response.body().toString())
                val list = ArrayList<YoutubeVideo>()
                for (item in response.body()?.items!!) {
                    list.add(YoutubeSearchItemMapper().toTarget(item))
                }
                data.postValue(list)
            }

            override fun onFailure(call: Call<YoutubeSearchResponseDto>, t: Throwable) {
                Log.d(TAG, "ERROR ON YOUTUBE SEARCH: $t")
                errorMessage.postValue(t.message)
            }
        })

    }

}

class SearchActivityViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchFragmentViewModel(mApplication as ApplicationCore) as T
    }
}