package com.dicoding.footy.data.remote.service

import com.dicoding.footy.data.remote.response.fixturesByTeam.FixturesByTeamResponse
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
}