package com.newsoft.softplayer.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.newsoft.softplayer.R
import com.newsoft.softplayer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //    private val newMusicActivityRequestCode = 1
    lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initializeBinding()

    }

    private fun initializeBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            mainLyricsButton.setOnClickListener {
                startLyricsActivity()
            }
            mainSearchButton.setOnClickListener {
                startSearchActivity()
            }
        }

    }


    private fun startLyricsActivity() {
        val intent = Intent(this, LyricsListActivity::class.java)
        startActivity(intent)
    }

    private fun startSearchActivity() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

//    private val getResult =
//        registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) {
//            if (it.resultCode == Activity.RESULT_OK) {
//                it.data?.getStringExtra(MainActivityViewModel.EXTRA_REPLY)?.let { innerIt ->
//                    val music = Music(innerIt)
//                    musicViewModel.insert(music)
////                    Toast.makeText(
////                        applicationContext,
////                        innerIt,
////                        Toast.LENGTH_LONG
////                    ).show()
//                }
//            } else {
//                Toast.makeText(
//                    applicationContext,
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        }


//    private fun initializeProperties() {
//
////        musicList = DataSource(this).getMusicList()
//        recyclerView = findViewById(R.id.music_recycler_view)
//        musicViewModel.allMusics.observe(this) {
//            // Update the cached copy of the words in the adapter.
//            recyclerView.adapter = MusicItemAdapter(it)
//        }
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        binding.startButton.setOnClickListener {
//            val intent = Intent(this@MainActivity, MainActivityViewModel::class.java)
//            getResult.launch(intent)
//        }
//
//    }

}