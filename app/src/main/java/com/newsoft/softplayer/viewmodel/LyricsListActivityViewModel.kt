package com.newsoft.softplayer.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.newsoft.softplayer.application.ApplicationCore
import com.newsoft.softplayer.framework.infrastructure.dbcontext.AppDatabase
import com.newsoft.softplayer.framework.infrastructure.entity.Music
import kotlinx.coroutines.launch


class LyricsListActivityViewModel(app: ApplicationCore) : AndroidViewModel(app) {

    var allMusics: MutableLiveData<List<Music>> = MutableLiveData()

    fun getAllMusicsObservers(): MutableLiveData<List<Music>> {
        return allMusics
    }

    fun getAllMusics() {
        val musicDao = AppDatabase.getDatabase(getApplication()).musicDao()
        val list = musicDao.getMusics()
        allMusics.postValue(list)
    }

    fun insertMusic(music: Music) {
        viewModelScope.launch {
            val musicDao = AppDatabase.getDatabase(getApplication()).musicDao()
            val list = musicDao.insertMusic(music)
            getAllMusics()
        }
    }

    fun updateMusic(music: Music) {
        val musicDao = AppDatabase.getDatabase(getApplication()).musicDao()
        val list = musicDao.updateMusic(music)
        getAllMusics()
    }

    fun deleteMusic(music: Music) {
        val musicDao = AppDatabase.getDatabase(getApplication()).musicDao()
        val list = musicDao.deleteMusic(music)
        getAllMusics()
    }

}

class LyricsListActivityViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LyricsListActivityViewModel(mApplication as ApplicationCore) as T
    }
}
