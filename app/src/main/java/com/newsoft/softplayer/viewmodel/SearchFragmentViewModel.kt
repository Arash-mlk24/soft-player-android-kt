package com.newsoft.softplayer.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newsoft.softplayer.application.ApplicationCore
import com.newsoft.softplayer.core.AppError
import com.newsoft.softplayer.core.contract.YoutubeAggregator
import com.newsoft.softplayer.framework.common.mappers.YoutubeSearchItemMapper
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.framework.network.dto.youtube.YoutubeSearchResponseDto
import com.newsoft.softplayer.framework.network.service.YoutubeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    app: ApplicationCore,
    private val youtubeAggregator: YoutubeAggregator
) : AndroidViewModel(app) {

    private val data: MutableLiveData<List<YoutubeVideo>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getDataObserver(): MutableLiveData<List<YoutubeVideo>> {
        return data
    }

    fun getErrorMessageObserver(): MutableLiveData<String> {
        return errorMessage
    }

    fun setErrorMessage(errorText: String) {
        errorMessage.postValue(errorText)
    }

    fun searchTerm(term: String) {

        try {
            val items = youtubeAggregator.getRemoteRepo()
                .getAll()
            data.postValue(items ?: throw AppError("NULL_POINTER_EXCEPTION"))
        } catch (exception: AppError) {
            errorMessage.postValue(exception.message)
        }

    }

}
