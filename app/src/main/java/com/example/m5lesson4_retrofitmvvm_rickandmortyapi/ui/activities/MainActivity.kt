package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.R
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.databinding.ActivityMainBinding
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.viewmodels.OnBoardViewModel
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: OnBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragments, CharactersFragment())
//            .commit()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragments) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavView.setupWithNavController(navController)
        binding.bottomNavView.visibility = if (viewModel.isFirstTime) View.GONE else View.VISIBLE
    }
}