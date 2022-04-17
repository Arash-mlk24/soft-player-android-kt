package com.newsoft.softplayer.view.viewmodel

import androidx.lifecycle.*
import com.newsoft.softplayer.data.model.entity.Music
import com.newsoft.softplayer.temp.repository.MusicRepository
import kotlinx.coroutines.launch

class MusicViewModel(private val repository: MusicRepository): ViewModel() {

    val allMusics: LiveData<List<Music>> = repository.allMusics.asLiveData()

    fun insert(music: Music) = viewModelScope.launch {
        repository.insert(music)
    }
}

class MusicViewModelFactory(private val repository: MusicRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MusicViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}