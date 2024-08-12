package com.dicoding.footy.data.repository

import android.util.Log
import com.dicoding.footy.data.remote.service.FootyApiService
import com.dicoding.footy.domain.model.FavoriteTeamItem
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.repository.FootyRepository
import com.dicoding.footy.utils.timestampToLocalDate
import com.dicoding.footy.utils.timestampToLocalTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootyRepositoryImpl @Inject constructor(
    private val footyApiService: FootyApiService,
): FootyRepository {

    override suspend fun searchTeam(searchQuery: String): Flow<List<FavoriteTeamItem>> {
        return withContext(Dispatchers.IO) {
            try {
                val teamSearchResponse = footyApiService.searchTeam(searchQuery)
                val teamList: List<FavoriteTeamItem> =
                teamSearchResponse.response
                    ?.mapNotNull {
                        FavoriteTeamItem (
                            id = it?.team?.id ?: 0,
                            badge = it?.team?.logo ?: "",
                            name = it?.team?.name ?: "",
                            country = it?.team?.country ?: "",
                        )
                    } ?: emptyList()

                return@withContext flowOf(teamList)
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }

    override suspend fun getNextFixturesByTeam(next: Int, team: Int): Flow<MatchInfo> {
        return withContext(Dispatchers.IO) {
            try {
                val fixturesByTeamResponse = footyApiService.getNextFixturesByTeam(next, team)
                val match = fixturesByTeamResponse.response?.first()
                val matchInfo = MatchInfo(
                    id = match?.fixture?.id ?: 0,
                    date = timestampToLocalDate(match?.fixture?.timestamp.toString(), match?.fixture?.timezone.toString()),
                    time = timestampToLocalTime(match?.fixture?.timestamp.toString(), match?.fixture?.timezone.toString()),
                    status = match?.fixture?.status?.long,
                    team1Score = match?.goals?.home.toString(),
                    team1Badge = match?.teams?.home?.logo,
                    team1Name = match?.teams?.home?.name,
                    team1Status = "Home",
                    team2Score = match?.goals?.away.toString(),
                    team2Badge = match?.teams?.away?.logo,
                    team2Name = match?.teams?.away?.name,
                    team2Status = "Away",
                    stadium = match?.fixture?.venue?.name,
                    competition = match?.league?.name,
                )

                return@withContext flowOf(matchInfo)
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }
}