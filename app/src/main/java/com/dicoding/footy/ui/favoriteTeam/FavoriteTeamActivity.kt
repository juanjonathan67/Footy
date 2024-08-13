package com.dicoding.footy.ui.favoriteTeam

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.footy.MainActivity
import com.dicoding.footy.databinding.ActivityFavoriteTeamBinding
import com.dicoding.footy.domain.model.FavoriteTeam
import com.dicoding.footy.domain.repository.UserPreferencesRepository
import com.dicoding.footy.utils.UiState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteTeamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteTeamBinding
    private lateinit var favoriteTeamAdapter: FavoriteTeamAdapter
    private val favoriteTeamViewModel: FavoriteTeamViewModel by viewModels()

    @Inject lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            if (userPreferencesRepository.getFavoriteTeamId().first() != 0) {
                startActivity(Intent(this@FavoriteTeamActivity, MainActivity::class.java))
            }
        }
        binding = ActivityFavoriteTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBarFavoriteTeam.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                favoriteTeamViewModel.searchTeam(binding.searchBarFavoriteTeam.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }

        favoriteTeamAdapter = FavoriteTeamAdapter(emptyList(), object : FavoriteTeamAdapter.OnItemClickCallback {
            override fun onItemClicked(favoriteTeam: FavoriteTeam) {
                MaterialAlertDialogBuilder(this@FavoriteTeamActivity)
                    .setTitle("Choose Favorite Team")
                    .setMessage("Are you sure to pick ${favoriteTeam.name} as your favorite Team?")
                    .setNeutralButton("Cancel") { dialog, _ ->
                        dialog.cancel()
                    }
                    .setPositiveButton("Confirm") { dialog, _ ->
                        runBlocking { userPreferencesRepository.saveFavoriteTeamId(favoriteTeam.id) }
                        dialog.dismiss()
                        startActivity(Intent(this@FavoriteTeamActivity, MainActivity::class.java))
                    }
                    .show()
            }
        })

        binding.rvFavoriteTeamResult.layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteTeamResult.adapter = favoriteTeamAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoriteTeamViewModel.teamList
                    .collect {
                        when (it) {
                            is UiState.Error -> {}
                            UiState.Loading -> {}
                            is UiState.Success -> { favoriteTeamAdapter.updateTeamList(it.data) }
                        }
                    }
            }
        }
    }
}