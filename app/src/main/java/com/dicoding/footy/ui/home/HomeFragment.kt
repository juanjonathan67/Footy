package com.dicoding.footy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.dicoding.footy.databinding.FragmentHomeBinding
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.repository.UserPreferencesRepository
import com.dicoding.footy.ui.favoriteTeam.FavoriteTeamViewModel
import com.dicoding.footy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getNextMatchInfo(1, runBlocking { userPreferencesRepository.getFavoriteTeamId().first() })

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Check local database for created at if it is a new day
                homeViewModel.nextMatchInfo
                    .collect {
                        when (it) {
                            is UiState.Error -> {}
                            UiState.Loading -> {}
                            is UiState.Success -> {
                                setHeaderData(matchInfo = it.data)
                            }
                        }
                    }
            }
        }
    }

    private fun setHeaderData(matchInfo: MatchInfo) {
        binding.headerHome.tvHeaderMatchDatetime.text = "${matchInfo.date}\n${matchInfo.time}"
        binding.headerHome.tvHeaderTeam1Name.text = matchInfo.team1Name
        binding.headerHome.tvHeaderTeam1Status.text = matchInfo.team1Status
        binding.headerHome.tvHeaderTeam2Name.text = matchInfo.team2Name
        binding.headerHome.tvHeaderTeam2Status.text = matchInfo.team2Status
        binding.headerHome.tvHeaderMatchLabel.text = matchInfo.status
        binding.headerHome.tvHeaderCompetitionName.text = matchInfo.competition
        binding.headerHome.tvHeaderStadiumName.text = matchInfo.stadium

        val stringBuilder = StringBuilder()
        val team1Score = matchInfo.team1Score
        if (team1Score == "null") {
            stringBuilder.append("0 - ")
        } else {
            stringBuilder.append("$team1Score -")
        }
        val team2Score = matchInfo.team2Score
        if (team2Score == "null") {
            stringBuilder.append("0")
        } else {
            stringBuilder.append(team2Score)
        }
        binding.headerHome.tvHeaderMatchScore.text = stringBuilder.toString()

        Glide.with(requireContext())
            .load(matchInfo.team1Badge)
            .into(binding.headerHome.ivHeaderTeam1Badge)

        Glide.with(requireContext())
            .load(matchInfo.team2Badge)
            .into(binding.headerHome.ivHeaderTeam2Badge)
    }
}