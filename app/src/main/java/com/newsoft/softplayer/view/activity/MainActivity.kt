package com.newsoft.softplayer.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.newsoft.softplayer.R
import com.newsoft.softplayer.databinding.ActivityMainBinding
import com.newsoft.softplayer.view.fragment.LibraryFragment
import com.newsoft.softplayer.view.fragment.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

            bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

            loadFragment(SearchFragment())

        }


        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_search -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.bottom_nav_library -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }

    }

    private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.bottom_nav_library -> {
                    fragment = LibraryFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_search -> {
                    fragment = SearchFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_constraint_layout, fragment)
        fragmentTransaction.commit()
    }

}