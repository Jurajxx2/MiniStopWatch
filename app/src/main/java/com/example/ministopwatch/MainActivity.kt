package com.example.ministopwatch

import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.base.BaseActivity
import com.example.ministopwatch.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationBarView.OnItemSelectedListener {

    override val layout = R.layout.activity_main

    override fun setupActivity() {
        binding.navigationView.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.home -> findNavController(R.id.nav_host_fragment).navigate(R.id.openHome)
            R.id.history -> findNavController(R.id.nav_host_fragment).navigate(R.id.openHistory)
            else -> findNavController(R.id.nav_host_fragment).navigate(R.id.openHistory)
        }

        return true
    }
}