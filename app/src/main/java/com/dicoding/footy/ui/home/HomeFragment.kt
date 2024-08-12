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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

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

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // TODO Check local database for created at if it is a new day
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
        binding.headerHome.tvHeaderMatchScore.text = "${matchInfo.team1Score} - ${matchInfo.team2Score}"

        Glide.with(requireContext())
            .load(matchInfo.team1Badge)
            .into(binding.headerHome.ivHeaderTeam1Badge)

        Glide.with(requireContext())
            .load(matchInfo.team2Badge)
            .into(binding.headerHome.ivHeaderTeam2Badge)
    }
}