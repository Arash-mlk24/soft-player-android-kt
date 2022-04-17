package com.newsoft.softplayer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.newsoft.softplayer.R
import com.newsoft.softplayer.data.model.datamodel.CurrentMusic
import com.newsoft.softplayer.data.model.datasource.DataSource
import com.newsoft.softplayer.databinding.ActivityMainBinding
import com.newsoft.softplayer.temp.adapter.MusicItemAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val currentMusic: CurrentMusic = CurrentMusic("Adele - Skyfall")
    private lateinit var musicList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeProperties()

    }


    private fun initializeBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.currentMusic = currentMusic
//        binding.invalidateAll()

    }

    private fun initializeProperties() {

        musicList = DataSource(this).getMusicList()
        val recyclerView: RecyclerView = findViewById(R.id.music_recycler_view)
        recyclerView.adapter = MusicItemAdapter(musicList)
        binding.startButton.setOnClickListener {
            Toast.makeText(this, "starting...", Toast.LENGTH_SHORT).show()
        }

    }

}