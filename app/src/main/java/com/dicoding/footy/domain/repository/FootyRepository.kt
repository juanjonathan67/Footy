package com.dicoding.footy.domain.repository

import com.dicoding.footy.domain.model.FavoriteTeamItem
import com.dicoding.footy.domain.model.MatchInfo
import kotlinx.coroutines.flow.Flow

interface FootyRepository {
    suspend fun searchTeam(
        searchQuery: String = "",
    ): Flow<List<FavoriteTeamItem>>

    suspend fun getNextFixturesByTeam(
        next: Int = 5,
        team: Int = 33,
    ): Flow<MatchInfo>
}