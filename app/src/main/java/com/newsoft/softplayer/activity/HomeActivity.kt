package com.newsoft.softplayer.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.newsoft.softplayer.R
import com.newsoft.softplayer.databinding.ActivityHomeBinding
import com.newsoft.softplayer.model.datamodel.CurrentMusic

class HomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private val currentMusic: CurrentMusic = CurrentMusic("Adele - Skyfall")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

    }

    private fun initializeBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.currentMusic = currentMusic
//        binding.invalidateAll()

    }

}