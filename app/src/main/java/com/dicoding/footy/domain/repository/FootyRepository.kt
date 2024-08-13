package com.dicoding.footy.domain.repository

import com.dicoding.footy.data.remote.response.events.EventsItem
import com.dicoding.footy.data.remote.response.fixturesByTeam.FixturesItem
import com.dicoding.footy.data.remote.response.head2Head.HeadToHeadItem
import com.dicoding.footy.data.remote.response.lineups.LineupsItem
import com.dicoding.footy.data.remote.response.statistics.StatisticsItem
import com.dicoding.footy.data.remote.response.statistics.StatsItem
import com.dicoding.footy.data.remote.response.teamSearch.TeamSearchItem
import kotlinx.coroutines.flow.Flow

interface FootyRepository {
    suspend fun searchTeam(
        searchQuery: String = "",
    ): Flow<List<TeamSearchItem>>

    suspend fun getNextFixturesByTeam(
        next: Int = 5,
        team: Int = 33,
    ): Flow<List<FixturesItem>>

    suspend fun getFixtureEvents(
        fixtureId: Int,
    ): Flow<List<EventsItem>>

    suspend fun getFixtureStatistics(
        fixtureId: Int,
    ): Flow<List<StatisticsItem>>

    suspend fun getFixtureLineups(
        fixtureId: Int,
    ): Flow<List<LineupsItem>>

    suspend fun getFixtureHeadToHead(
        last: Int,
        h2h: String,
    ): Flow<List<HeadToHeadItem>>
}