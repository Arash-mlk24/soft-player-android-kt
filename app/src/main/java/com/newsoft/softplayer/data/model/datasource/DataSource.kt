package com.newsoft.softplayer.data.model.datasource

import android.content.Context
import com.newsoft.softplayer.R

class DataSource(private val context: Context) {

    fun getMusicList(): Array<String> {
        return context.resources.getStringArray(R.array.music_array)
    }

}