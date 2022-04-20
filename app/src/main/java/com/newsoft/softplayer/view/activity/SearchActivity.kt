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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.newsoft.softplayer.R
import com.newsoft.softplayer.databinding.ActivitySearchBinding
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.view.component.YoutubeItemAdapter
import com.newsoft.softplayer.viewmodel.SearchActivityViewModel
import com.newsoft.softplayer.viewmodel.SearchActivityViewModelFactory

class SearchActivity : AppCompatActivity(), YoutubeItemAdapter.YoutubeItemClickListener {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchActivityViewModel by viewModels {
        SearchActivityViewModelFactory(
            application
        )
    }
    lateinit var recyclerView: RecyclerView
    lateinit var youtubeItemAdapter: YoutubeItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        bindView()

        initializeBinding()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeBinding() {

        binding.apply {

            // set button handler
            submitButton.setOnClickListener {

                val inputText: String = youtubeSearchInput.text.toString()
                if (inputText.isEmpty()) {
                    Toast.makeText(
                        this@SearchActivity,
                        "Search Field is Empty!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.searchTerm(inputText)
                }

            }

            // bind recycler view
            youtubeVideosRecyclerView.apply {

                layoutManager = LinearLayoutManager(this@SearchActivity)
                youtubeItemAdapter = YoutubeItemAdapter(this@SearchActivity)
                adapter = youtubeItemAdapter
                val divider = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
                addItemDecoration(divider)

            }

        }

        // observe error text
        viewModel.getErrorMessageObserver().observe(this@SearchActivity, Observer {

            binding.searchErrorText.text = it
            youtubeItemAdapter.notifyDataSetChanged()

        })

        // observe data
        viewModel.getDataObserver().observe(this@SearchActivity, Observer {

            youtubeItemAdapter.setListData(it as ArrayList<YoutubeVideo>)
            youtubeItemAdapter.notifyDataSetChanged()

        })

    }


    private fun bindView() {

        // bind activity to xml
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

    }

    override fun onImageClickListener(youtubeVideo: YoutubeVideo) {

        Toast.makeText(
            this@SearchActivity,
            "${youtubeVideo.title} clicked!",
            Toast.LENGTH_SHORT
        ).show()

    }

}