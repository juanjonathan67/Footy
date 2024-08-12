package com.dicoding.footy.ui.favoriteTeam

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.footy.databinding.ActivityFavoriteTeamBinding
import com.dicoding.footy.domain.model.FavoriteTeamItem
import com.dicoding.footy.domain.repository.UserPreferencesRepository
import com.dicoding.footy.utils.UiState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
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
        binding = ActivityFavoriteTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBarFavoriteTeam.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                favoriteTeamViewModel.searchTeam(binding.searchBarFavoriteTeam.text.toString())
            }
        }

        favoriteTeamAdapter = FavoriteTeamAdapter(emptyList(), object : FavoriteTeamAdapter.OnItemClickCallback {
            override fun onItemClicked(favoriteTeamItem: FavoriteTeamItem) {
                MaterialAlertDialogBuilder(this@FavoriteTeamActivity)
                    .setTitle("Choose Favorite Team")
                    .setMessage("Are you sure to pick ${favoriteTeamItem.name} as your favorite Team?")
                    .setNeutralButton("Cancel") { dialog, _ ->
                        dialog.cancel()
                    }
                    .setPositiveButton("Confirm") { dialog, _ ->
                        runBlocking { userPreferencesRepository.saveFavoriteTeamId(favoriteTeamItem.id) }
                        dialog.dismiss()
                    }
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