package com.dicoding.footy.data.remote.service

import com.dicoding.footy.data.remote.response.events.EventsResponse
import com.dicoding.footy.data.remote.response.fixturesByTeam.FixturesByTeamResponse
import com.dicoding.footy.data.remote.response.head2Head.Head2HeadResponse
import com.dicoding.footy.data.remote.response.lineups.LineupsResponse
import com.dicoding.footy.data.remote.response.statistics.StatisticsResponse
import com.dicoding.footy.data.remote.response.teamSearch.TeamSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FootyApiService {
    @GET("teams")
    suspend fun searchTeam(
        @Query("search") searchQuery: String,
    ): TeamSearchResponse

    @GET("fixtures")
    suspend fun getNextFixturesByTeam(
        @Query("next") next: Int,
        @Query("team") team: Int,
    ): FixturesByTeamResponse

    @GET("fixtures/events")
    suspend fun getFixtureEvents(
        @Query("fixture") fixtureId: Int,
    ): EventsResponse

    @GET("fixtures/statistics")
    suspend fun getFixtureStatistics(
        @Query("fixture") fixtureId: Int,
    ): StatisticsResponse

    @GET("fixtures/lineups")
    suspend fun getFixtureLineups(
        @Query("fixture") fixtureId: Int,
    ): LineupsResponse

    @GET("fixtures/headtohead")
    suspend fun getFixtureHeadToHead(
        @Query("last") last: Int,
        @Query("h2h") h2h: String,
    ) : Head2HeadResponse
}