package com.dicoding.footy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.footy.databinding.FragmentHomeBinding
import com.dicoding.footy.domain.model.Event
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.repository.UserPreferencesRepository
import com.dicoding.footy.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var fixtureEventsAdapter: FixtureEventsAdapter
    private val binding get() = _binding

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject lateinit var userPreferencesRepository: UserPreferencesRepository

    private var homeTeamId = 0
    private var awayTeamId = 0
    private var fixtureId = 0

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
                                homeTeamId = it.data.team1Id
                                awayTeamId = it.data.team2Id
                                fixtureId = it.data.fixtureId
                                setHeaderData(matchInfo = it.data)
                                setMatchEventsData()
                                setStatisticsData()
                            }
                        }
                    }
            }
        }

        binding.matchEvents.rvMatchEvents.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setHeaderData(matchInfo: MatchInfo) {
        binding.headerHome.tvHeaderMatchDatetime.text = "${matchInfo.date}\n${matchInfo.time}"
        binding.headerHome.tvHeaderTeam1Name.text = matchInfo.team1Name
        binding.headerHome.tvHeaderTeam1Status.text = matchInfo.team1Status
        binding.headerHome.tvHeaderTeam2Name.text = matchInfo.team2Name
        binding.headerHome.tvHeaderTeam2Status.text = matchInfo.team2Status
        binding.headerHome.tvHeaderMatchScore.text = "${matchInfo.team1Score} - ${matchInfo.team2Score}"
        binding.headerHome.tvHeaderMatchLabel.text = matchInfo.status
        binding.headerHome.tvHeaderCompetitionName.text = matchInfo.competition
        binding.headerHome.tvHeaderStadiumName.text = matchInfo.stadium

        Glide.with(requireContext())
            .load(matchInfo.team1Badge)
            .into(binding.headerHome.ivHeaderTeam1Badge)

        Glide.with(requireContext())
            .load(matchInfo.team2Badge)
            .into(binding.headerHome.ivHeaderTeam2Badge)
    }

    private fun setMatchEventsData() {
//        homeViewModel.getFixtureEvents(fixtureId)
        homeViewModel.getFixtureEvents(1035490)
        fixtureEventsAdapter = FixtureEventsAdapter(emptyList(), homeTeamId)
        binding.matchEvents.rvMatchEvents.adapter = fixtureEventsAdapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.fixtureEvents
                    .collect {
                        when (it) {
                            is UiState.Error -> {}
                            UiState.Loading -> {}
                            is UiState.Success -> { fixtureEventsAdapter.updateEventsList(it.data) }
                        }
                    }
            }
        }
    }

    private fun setStatisticsData() {
//        homeViewModel.getFixtureStatistics(fixtureId)
        homeViewModel.getFixtureStatistics(1035490)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.fixtureStatistics
                    .collect {
                        when (it) {
                            is UiState.Error -> {}
                            UiState.Loading -> {}
                            is UiState.Success -> {
                                var totalTemp = it.data.shotsOnGoalHome + it.data.shotsOnGoalAway
                                binding.matchStatistics.shotsOnGoalStatsLeft.layoutParams.width = (it.data.shotsOnGoalHome * 125.0 / totalTemp ).roundToInt()
                                binding.matchStatistics.shotsOnGoalStatsLeftValue.text = it.data.shotsOnGoalHome.toString()
                                binding.matchStatistics.shotsOnGoalStatsRight.layoutParams.width = (it.data.shotsOnGoalAway * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.shotsOnGoalStatsRightValue.text = it.data.shotsOnGoalAway.toString()
                                totalTemp = it.data.totalShotsHome + it.data.totalShotsAway
                                binding.matchStatistics.totalShotsStatsLeft.layoutParams.width = (it.data.totalShotsHome * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.totalShotsStatsLeftValue.text = it.data.totalShotsHome.toString()
                                binding.matchStatistics.totalShotsStatsRight.layoutParams.width = (it.data.totalShotsAway * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.totalShotsStatsRightValue.text = it.data.totalShotsAway.toString()
                                totalTemp = 100
                                binding.matchStatistics.ballPossessionStatsLeft.layoutParams.width = (it.data.ballPossessionHome * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.ballPossessionStatsLeftValue.text = "${it.data.ballPossessionHome}%"
                                binding.matchStatistics.ballPossessionStatsRight.layoutParams.width = (it.data.ballPossessionAway * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.ballPossessionStatsRightValue.text = "${it.data.ballPossessionAway}%"
                                totalTemp = it.data.foulsHome + it.data.foulsAway
                                binding.matchStatistics.foulsStatsLeft.layoutParams.width = (it.data.foulsHome * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.foulsStatsLeftValue.text = it.data.foulsHome.toString()
                                binding.matchStatistics.foulsStatsRight.layoutParams.width = (it.data.foulsAway * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.foulsStatsRightValue.text = it.data.foulsAway.toString()
                                totalTemp = 100
                                binding.matchStatistics.passAccuracyStatsLeft.layoutParams.width = (it.data.passAccuracyHome * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.passAccuracyStatsLeftValue.text = "${it.data.passAccuracyHome}%"
                                binding.matchStatistics.passAccuracyStatsRight.layoutParams.width = (it.data.passAccuracyAway * 125.0 / totalTemp).roundToInt()
                                binding.matchStatistics.passAccuracyStatsRightValue.text = "${it.data.passAccuracyAway}%"
                                val totalTempDouble: Double = it.data.expectedGoalsHome + it.data.expectedGoalsAway
                                binding.matchStatistics.expectedGoalsStatsLeft.layoutParams.width = (it.data.expectedGoalsHome * 125.0 / totalTempDouble).roundToInt()
                                binding.matchStatistics.expectedGoalsStatsLeftValue.text = it.data.expectedGoalsHome.toString()
                                binding.matchStatistics.expectedGoalsStatsRight.layoutParams.width = (it.data.expectedGoalsAway * 125.0 / totalTempDouble).roundToInt()
                                binding.matchStatistics.expectedGoalsStatsRightValue.text = it.data.expectedGoalsAway.toString()
                            }
                        }
                    }
            }
        }
    }
}