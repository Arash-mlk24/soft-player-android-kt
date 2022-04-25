package com.newsoft.softplayer.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.newsoft.softplayer.R
import com.newsoft.softplayer.databinding.FragmentSearchBinding
import com.newsoft.softplayer.framework.infrastructure.entity.YoutubeVideo
import com.newsoft.softplayer.view.component.YoutubeItemAdapter
import com.newsoft.softplayer.viewmodel.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), YoutubeItemAdapter.YoutubeItemClickListener {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchFragmentViewModel by viewModels()
    lateinit var youtubeItemAdapter: YoutubeItemAdapter


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

        observeErrorText()

        observeData()

        return binding.root

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initializeBinding() {

        binding.apply {

            // bind recycler view
            youtubeVideosRecyclerView.apply {

                layoutManager = LinearLayoutManager(this@SearchFragment.activity)
                youtubeItemAdapter = YoutubeItemAdapter(this@SearchFragment)
                adapter = youtubeItemAdapter
                val divider = DividerItemDecoration(
                    this@SearchFragment.context,
                    StaggeredGridLayoutManager.VERTICAL
                )
                addItemDecoration(divider)

            }

            // set button handler
            submitButton.setOnClickListener {

                val inputText: String = youtubeSearchInput.text.toString()
                if (inputText.isEmpty()) {
                    Toast.makeText(
                        this@SearchFragment.activity,
                        "Search Field is Empty!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.searchTerm(inputText)
                }

            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() {
        viewModel.getDataObserver().observe(viewLifecycleOwner, Observer {

            youtubeItemAdapter.setListData(it as ArrayList<YoutubeVideo>)
            youtubeItemAdapter.notifyDataSetChanged()

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeErrorText() {
        viewModel.getErrorMessageObserver().observe(viewLifecycleOwner, Observer {

            binding.searchErrorText.text = it
            youtubeItemAdapter.notifyDataSetChanged()

        })
    }

    private fun bindView(inflater: LayoutInflater, container: ViewGroup) {

        // bind activity to xml
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

    }

    override fun onImageClickListener(youtubeVideo: YoutubeVideo) {

        Toast.makeText(
            this@SearchFragment.context,
            "${youtubeVideo.title} clicked!",
            Toast.LENGTH_SHORT
        ).show()

    }

}