package com.dicoding.footy.data.repository

import com.dicoding.footy.data.remote.response.events.EventsItem
import com.dicoding.footy.data.remote.response.fixturesByTeam.FixturesItem
import com.dicoding.footy.data.remote.response.head2Head.HeadToHeadItem
import com.dicoding.footy.data.remote.response.lineups.LineupsItem
import com.dicoding.footy.data.remote.response.statistics.StatisticsItem
import com.dicoding.footy.data.remote.response.teamSearch.TeamSearchItem
import com.dicoding.footy.data.remote.service.FootyApiService
import com.dicoding.footy.domain.repository.FootyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootyRepositoryImpl @Inject constructor(
    private val footyApiService: FootyApiService,
): FootyRepository {

    override suspend fun searchTeam(searchQuery: String): Flow<List<TeamSearchItem>> {
        return withContext(Dispatchers.IO) {
            try {
                flow {
                    val teamSearchResponse = footyApiService.searchTeam(searchQuery)
                    val teamSearchItem = teamSearchResponse.response
                    if (teamSearchItem != null) {
                        emit(teamSearchItem.mapNotNull { it })
                    }
                }
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }

    override suspend fun getNextFixturesByTeam(next: Int, team: Int): Flow<List<FixturesItem>> {
        return withContext(Dispatchers.IO) {
            try {
                flow {
                    val fixturesByTeamResponse = footyApiService.getNextFixturesByTeam(next, team)
                    val fixturesItem = fixturesByTeamResponse.response
                    if (fixturesItem != null) {
                        emit(fixturesItem.mapNotNull { it })
                    }
                }
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }

    override suspend fun getFixtureEvents(fixtureId: Int): Flow<List<EventsItem>> {
        return withContext(Dispatchers.IO) {
            try {
                flow {
                    val eventsResponse = footyApiService.getFixtureEvents(fixtureId)
                    val eventsItem = eventsResponse.response
                    if (eventsItem != null) {
                        emit(eventsItem.mapNotNull { it })
                    }
                }
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }

    override suspend fun getFixtureStatistics(fixtureId: Int): Flow<List<StatisticsItem>> {
        return withContext(Dispatchers.IO) {
            try {
                flow {
                    val statisticsResponse = footyApiService.getFixtureStatistics(fixtureId)
                    val statisticsItems = statisticsResponse.response
                    if (statisticsItems != null) {
                        emit(statisticsItems.mapNotNull { it })
                    }
                }
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }

    override suspend fun getFixtureLineups(fixtureId: Int): Flow<List<LineupsItem>> {
        return withContext(Dispatchers.IO) {
            try {
                flow {
                    val lineupsResponse = footyApiService.getFixtureLineups(fixtureId)
                    val lineupsItems = lineupsResponse.response
                    if (lineupsItems != null) {
                        emit(lineupsItems.mapNotNull { it })
                    }
                }
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }

    override suspend fun getFixtureHeadToHead(last: Int, h2h: String): Flow<List<HeadToHeadItem>> {
        return withContext(Dispatchers.IO) {
            try {
                flow {
                    val headToHeadResponse = footyApiService.getFixtureHeadToHead(last, h2h)
                    val headToHeadItems = headToHeadResponse.response
                    if (headToHeadItems != null) {
                        emit(headToHeadItems.mapNotNull { it })
                    }
                }
            } catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }
}