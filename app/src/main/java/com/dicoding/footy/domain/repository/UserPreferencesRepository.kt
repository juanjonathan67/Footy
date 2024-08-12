package com.dicoding.footy.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun getFavoriteTeamId(): Flow<Int>

    suspend fun saveFavoriteTeamId(favoriteTeamId: Int)

    suspend fun deleteFavoriteTeamId()
}