package com.dicoding.footy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.footy.domain.model.FavoriteTeamItem
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.repository.FootyRepository
import com.dicoding.footy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val footyRepository: FootyRepository,
): ViewModel() {
    private val _nextMatchInfo: MutableStateFlow<UiState<MatchInfo>> = MutableStateFlow(UiState.Loading)
    val nextMatchInfo: StateFlow<UiState<MatchInfo>> get() = _nextMatchInfo

    fun getNextMatchInfo(
        next: Int,
        team: Int,
    ) {
        viewModelScope.launch {
            try {
                footyRepository.getNextFixturesByTeam(next, team)
                    .catch {
                        _nextMatchInfo.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _nextMatchInfo.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _nextMatchInfo.value = UiState.Error(e.message.toString())
            }
        }
    }

}