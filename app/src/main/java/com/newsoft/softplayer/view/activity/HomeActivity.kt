package com.newsoft.softplayer.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.newsoft.softplayer.R
import com.newsoft.softplayer.temp.adapter.MusicItemAdapter
import com.newsoft.softplayer.data.model.datasource.DataSource
import com.newsoft.softplayer.databinding.ActivityHomeBinding
import com.newsoft.softplayer.data.model.datamodel.CurrentMusic

class HomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private val currentMusic: CurrentMusic = CurrentMusic("Adele - Skyfall")
    private lateinit var musicList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initializeBinding()
        initializeProperties()

    }

    private fun initializeBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.currentMusic = currentMusic
//        binding.invalidateAll()

    }

    private fun initializeProperties() {

        musicList = DataSource(this).getMusicList()
        val recyclerView: RecyclerView = findViewById(R.id.music_recycler_view)
        recyclerView.adapter = MusicItemAdapter(musicList)

    }

}