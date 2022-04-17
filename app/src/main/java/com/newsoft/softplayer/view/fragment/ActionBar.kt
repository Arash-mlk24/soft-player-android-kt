package com.newsoft.softplayer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newsoft.softplayer.R

class ActionBar : Fragment() {

    fun newInstance(): ActionBar {
        return ActionBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_action_bar, container, false)
    }

}