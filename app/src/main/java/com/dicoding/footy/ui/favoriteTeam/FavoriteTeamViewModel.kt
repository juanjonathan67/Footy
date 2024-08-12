package com.dicoding.footy.ui.favoriteTeam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.footy.domain.model.FavoriteTeamItem
import com.dicoding.footy.domain.repository.FootyRepository
import com.dicoding.footy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTeamViewModel @Inject constructor(
    private val footyRepository: FootyRepository,
): ViewModel() {
    private val _teamList: MutableStateFlow<UiState<List<FavoriteTeamItem>>> = MutableStateFlow(UiState.Loading)
    val teamList: StateFlow<UiState<List<FavoriteTeamItem>>> get() = _teamList

    fun searchTeam(
        searchQuery: String
    ) {
        viewModelScope.launch {
            try {
                footyRepository.searchTeam(searchQuery)
                    .catch {
                        _teamList.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _teamList.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _teamList.value = UiState.Error(e.message.toString())
            }
        }
    }
}