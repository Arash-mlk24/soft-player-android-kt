package com.newsoft.softplayer.view.fragment

import android.annotation.SuppressLint
import android.app.Application
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.newsoft.softplayer.R
import com.newsoft.softplayer.databinding.FragmentLibraryBinding
import com.newsoft.softplayer.framework.infrastructure.entity.Music
import com.newsoft.softplayer.view.component.MusicItemAdapter
import com.newsoft.softplayer.viewmodel.LibraryFragmentViewModel
import com.newsoft.softplayer.viewmodel.LibraryFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_library.*


class LibraryFragment : Fragment(), MusicItemAdapter.MusicItemClickListener {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause: Boolean = false
    lateinit var binding: FragmentLibraryBinding
    lateinit var musicItemAdapter: MusicItemAdapter
    private val viewModel: LibraryFragmentViewModel by viewModels {
        LibraryFragmentViewModelFactory(
            this.activity?.application ?: Application()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreate(savedInstanceState)

        if (container != null) {
            bindView(inflater, container)
        }
        initializeBinding()

        return binding.root

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeBinding() {

        binding.apply {

            // bind recycler view
//            musicRecyclerView.apply {
//                layoutManager = LinearLayoutManager(this@LibraryFragment.activity)
//                musicItemAdapter = MusicItemAdapter(this@LibraryFragment)
//                adapter = musicItemAdapter
//                val divider = DividerItemDecoration(this@LibraryFragment.context, StaggeredGridLayoutManager.VERTICAL)
//                addItemDecoration(divider)
//            }

            // set button listener
//            addButton.setOnClickListener {
//                val musicName: String = musicItemInput.text.toString()
//                val music: Music = Music(musicName, "")
//                viewModel.insertMusic(music)
//            }

            // set play button listener
            playBtn.setOnClickListener {
                if (pause) {
                    mediaPlayer.seekTo(mediaPlayer.currentPosition)
                    mediaPlayer.start()
                    pause = false
                    Toast.makeText(
                        this@LibraryFragment.context,
                        "media playing",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {

                    mediaPlayer =
                        MediaPlayer.create(this@LibraryFragment.context, R.raw.megadeth_promises)
                    mediaPlayer.start()
                    Toast.makeText(
                        this@LibraryFragment.context,
                        "media playing",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                initializeSeekBar()
                playBtn.isEnabled = false
                pauseBtn.isEnabled = true
                stopBtn.isEnabled = true

                mediaPlayer.setOnCompletionListener {
                    playBtn.isEnabled = true
                    pauseBtn.isEnabled = false
                    stopBtn.isEnabled = false
                    Toast.makeText(this@LibraryFragment.context, "end", Toast.LENGTH_SHORT).show()
                }
            }

            pauseBtn.setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    pause = true
                    playBtn.isEnabled = true
                    pauseBtn.isEnabled = false
                    stopBtn.isEnabled = true
                    Toast.makeText(this@LibraryFragment.context, "media pause", Toast.LENGTH_SHORT).show()
                }
            }
            // Stop the media player
            stopBtn.setOnClickListener {
                if (mediaPlayer.isPlaying || pause.equals(true)) {
                    pause = false
                    seekBar.progress = 0
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    mediaPlayer.release()
                    handler.removeCallbacks(runnable)

                    playBtn.isEnabled = true
                    pauseBtn.isEnabled = false
                    stopBtn.isEnabled = false
                    tvPass.text = ""
                    tvDue.text = ""
                    Toast.makeText(this@LibraryFragment.context, "media stop", Toast.LENGTH_SHORT).show()
                }
            }
            // Seek bar change listener
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    if (b) {
                        mediaPlayer.seekTo(i * 1000)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                }

            })
        }

        // observe data
        viewModel.getAllMusicsObservers().observe(viewLifecycleOwner, Observer {
            musicItemAdapter.setListData(ArrayList(it))
            musicItemAdapter.notifyDataSetChanged()
        })

    }

    private fun initializeSeekBar() {
        binding.seekBar.max = mediaPlayer.seconds

        runnable = Runnable {
            binding.seekBar.progress = mediaPlayer.currentSeconds

            binding.tvPass.text = "${mediaPlayer.currentSeconds} sec"
            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
            binding.tvDue.text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }

    private fun bindView(inflater: LayoutInflater, container: ViewGroup) {

        // bind view to xml
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)

    }

    // adapter items click handler implementation
    override fun onItemClickListener(music: Music) {
        Toast.makeText(
            this.context,
            music.name,
            Toast.LENGTH_LONG
        ).show()
    }

}