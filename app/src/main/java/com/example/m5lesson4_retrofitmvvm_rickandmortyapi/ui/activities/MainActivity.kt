package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.R
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.fragments.CharactersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragments, CharactersFragment())
            .commit()
    }
}