package com.newsoft.softplayer.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.newsoft.softplayer.R
import com.newsoft.softplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeButton()
    }

    private fun initializeButton() {
        binding.startButton.setOnClickListener {
            Toast.makeText(this, "starting...", Toast.LENGTH_SHORT).show()
        }
    }

}