package com.dicoding.footy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.footy.domain.model.Event
import com.dicoding.footy.domain.model.Lineup
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.model.Statistics
import com.dicoding.footy.domain.useCase.FootyUseCase
import com.dicoding.footy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val footyUseCase: FootyUseCase,
): ViewModel() {
    private val _nextMatchInfo: MutableStateFlow<UiState<MatchInfo>> = MutableStateFlow(UiState.Loading)
    val nextMatchInfo: StateFlow<UiState<MatchInfo>> get() = _nextMatchInfo

    private val _fixtureEvents: MutableStateFlow<UiState<List<Event>>> = MutableStateFlow(UiState.Loading)
    val fixtureEvents: StateFlow<UiState<List<Event>>> get() = _fixtureEvents

    private val _fixtureStatistics: MutableStateFlow<UiState<Statistics>> = MutableStateFlow(UiState.Loading)
    val fixtureStatistics: StateFlow<UiState<Statistics>> get() = _fixtureStatistics

    private val _fixtureLineups: MutableStateFlow<UiState<List<Lineup>>> = MutableStateFlow(UiState.Loading)
    val fixtureLineups: StateFlow<UiState<List<Lineup>>> get() = _fixtureLineups

    private val _fixtureHeadToHead: MutableStateFlow<UiState<List<MatchInfo>>> = MutableStateFlow(UiState.Loading)
    val fixtureHeadToHead: StateFlow<UiState<List<MatchInfo>>> get() = _fixtureHeadToHead

    fun getNextMatchInfo(
        next: Int,
        team: Int,
    ) {
        viewModelScope.launch {
            try {
                footyUseCase.getNextFixturesByTeam(next, team)
                    .catch {
                        _nextMatchInfo.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _nextMatchInfo.value = UiState.Success(it[0])
                    }
            } catch (e: Exception) {
                _nextMatchInfo.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getFixtureEvents(
        fixtureId: Int,
    ) {
        viewModelScope.launch {
            try {
                footyUseCase.getFixtureEvents(fixtureId)
                    .catch {
                        _fixtureEvents.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _fixtureEvents.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _fixtureEvents.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getFixtureStatistics(
        fixtureId: Int
    ) {
        viewModelScope.launch {
            try {
                footyUseCase.getFixtureStatistics(fixtureId)
                    .catch {
                        _fixtureStatistics.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _fixtureStatistics.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _fixtureStatistics.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getFixtureLineups(
        fixtureId: Int
    ) {
        viewModelScope.launch {
            try {
                footyUseCase.getFixtureLineups(fixtureId)
                    .catch {
                        _fixtureLineups.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _fixtureLineups.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _fixtureLineups.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getFixtureHeadToHead(
        last: Int,
        h2h: String,
    ) {
        viewModelScope.launch {
            try {
                footyUseCase.getFixtureHeadToHead(last, h2h)
                    .catch {
                        _fixtureHeadToHead.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _fixtureHeadToHead.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _fixtureHeadToHead.value = UiState.Error(e.message.toString())
            }
        }
    }
}