package com.dicoding.footy.domain.useCase

import com.dicoding.footy.domain.model.Event
import com.dicoding.footy.domain.model.FavoriteTeam
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.model.Lineup
import com.dicoding.footy.domain.model.Statistics
import kotlinx.coroutines.flow.Flow

interface FootyUseCase {
    suspend fun searchTeam(searchQuery: String): Flow<List<FavoriteTeam>>

    suspend fun getNextFixturesByTeam(next: Int, team: Int): Flow<List<MatchInfo>>

    suspend fun getFixtureEvents(fixtureId: Int): Flow<List<Event>>

    suspend fun getFixtureStatistics(fixtureId: Int): Flow<Statistics>

    suspend fun getFixtureLineups(fixtureId: Int): Flow<List<Lineup>>

    suspend fun getFixtureHeadToHead(last: Int, h2h: String): Flow<List<MatchInfo>>
}