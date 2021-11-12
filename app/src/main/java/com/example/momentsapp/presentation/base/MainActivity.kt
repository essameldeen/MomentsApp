package com.example.momentsapp.presentation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.momentsapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}