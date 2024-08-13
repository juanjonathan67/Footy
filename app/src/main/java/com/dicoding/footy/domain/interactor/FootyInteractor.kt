package com.dicoding.footy.domain.interactor

import com.dicoding.footy.domain.model.Event
import com.dicoding.footy.domain.model.FavoriteTeam
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.model.Lineup
import com.dicoding.footy.domain.model.Statistics
import com.dicoding.footy.domain.repository.FootyRepository
import com.dicoding.footy.domain.useCase.FootyUseCase
import com.dicoding.footy.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FootyInteractor @Inject constructor(
    private val footyRepository: FootyRepository,
): FootyUseCase {
    override suspend fun searchTeam(searchQuery: String): Flow<List<FavoriteTeam>> {
        return flow {
            emit(
                DataMapper.teamSearchItemsToFavoriteTeamItems(
                    footyRepository
                        .searchTeam(searchQuery)
                        .first()
                )
            )
        }
    }

    override suspend fun getNextFixturesByTeam(next: Int, team: Int): Flow<List<MatchInfo>> {
        return flow {
            emit(
                DataMapper.fixturesItemsToMatchInfos(
                    footyRepository
                        .getNextFixturesByTeam(next, team)
                        .first()
                )
            )
        }
    }

    override suspend fun getFixtureEvents(fixtureId: Int): Flow<List<Event>> {
        return flow {
            emit(
                DataMapper.eventsItemsToEvents(
                    footyRepository
                        .getFixtureEvents(fixtureId)
                        .first()
                )
            )
        }
    }

    override suspend fun getFixtureStatistics(fixtureId: Int): Flow<Statistics> {
        return flow {
            emit(
                DataMapper.statisticsItemToStatistics(
                    footyRepository
                        .getFixtureStatistics(fixtureId)
                        .first()
                )
            )
        }
    }

    override suspend fun getFixtureLineups(fixtureId: Int): Flow<List<Lineup>> {
        return flow {
            emit(
                DataMapper.lineupsItemToLineup(
                    footyRepository
                        .getFixtureLineups(fixtureId)
                        .first()
                )
            )
        }
    }

    override suspend fun getFixtureHeadToHead(last: Int, h2h: String): Flow<List<MatchInfo>> {
        return flow {
            emit(
                DataMapper.headToHeadItemsToMatchInfos(
                    footyRepository
                        .getFixtureHeadToHead(last, h2h)
                        .first()
                )
            )
        }
    }
}