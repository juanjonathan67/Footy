package com.dicoding.footy.ui.favoriteTeam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.footy.domain.model.FavoriteTeam
import com.dicoding.footy.domain.useCase.FootyUseCase
import com.dicoding.footy.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTeamViewModel @Inject constructor(
    private val footyUseCase: FootyUseCase,
): ViewModel() {
    private val _teamList: MutableStateFlow<UiState<List<FavoriteTeam>>> = MutableStateFlow(UiState.Loading)
    val teamList: StateFlow<UiState<List<FavoriteTeam>>> get() = _teamList

    fun searchTeam(
        searchQuery: String
    ) {
        viewModelScope.launch {
            try {
                footyUseCase.searchTeam(searchQuery)
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