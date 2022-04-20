package com.newsoft.softplayer.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.newsoft.softplayer.R
import com.newsoft.softplayer.framework.infrastructure.entity.Music
import com.newsoft.softplayer.databinding.ActivityLyricsListBinding
import com.newsoft.softplayer.view.component.MusicItemAdapter
import com.newsoft.softplayer.viewmodel.LyricsListActivityViewModel
import com.newsoft.softplayer.viewmodel.LyricsListActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_lyrics_list.*


class LyricsListActivity: AppCompatActivity(), MusicItemAdapter.MusicItemClickListener {

    lateinit var binding: ActivityLyricsListBinding
    lateinit var musicItemAdapter: MusicItemAdapter
    private val viewModel: LyricsListActivityViewModel by viewModels { LyricsListActivityViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bindView()
        initializeBinding()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeBinding() {

        binding.apply {

            // bind recycler view
            musicRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@LyricsListActivity)
                musicItemAdapter = MusicItemAdapter(this@LyricsListActivity)
                adapter = musicItemAdapter
                val divider = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
                addItemDecoration(divider)
            }

            // set button listener
            addButton.setOnClickListener {
                val musicName: String = music_item_input.text.toString()
                val music: Music = Music(musicName, "")
                viewModel.insertMusic(music)
            }

        }

        // observe data
        viewModel.getAllMusicsObservers().observe(this, Observer {
            musicItemAdapter.setListData(ArrayList(it))
            musicItemAdapter.notifyDataSetChanged()
        })

    }

    private fun bindView() {

        // bind view to xml
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lyrics_list)

    }

    // adapter items click handler implementation
    override fun onItemClickListener(music: Music) {
        Toast.makeText(
            applicationContext,
            music.name,
            Toast.LENGTH_LONG
        ).show()
    }

}