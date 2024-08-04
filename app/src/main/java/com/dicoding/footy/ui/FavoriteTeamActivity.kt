package com.dicoding.footy.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.footy.R
import com.dicoding.footy.databinding.ActivityFavoriteTeamBinding

class FavoriteTeamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteTeamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}