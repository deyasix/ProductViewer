package com.example.productviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.productviewer.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavGraph()
    }

    private fun setupNavGraph() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController
        navController.setGraph(R.navigation.main_navigation)
    }
}