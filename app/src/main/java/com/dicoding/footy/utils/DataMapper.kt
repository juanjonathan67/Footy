package com.dicoding.footy.utils

import com.dicoding.footy.data.remote.response.events.EventsItem
import com.dicoding.footy.data.remote.response.events.Time
import com.dicoding.footy.data.remote.response.fixturesByTeam.FixturesItem
import com.dicoding.footy.data.remote.response.head2Head.HeadToHeadItem
import com.dicoding.footy.data.remote.response.lineups.LineupsItem
import com.dicoding.footy.data.remote.response.statistics.StatisticsItem
import com.dicoding.footy.data.remote.response.teamSearch.TeamSearchItem
import com.dicoding.footy.domain.model.Event
import com.dicoding.footy.domain.model.EventType
import com.dicoding.footy.domain.model.FavoriteTeam
import com.dicoding.footy.domain.model.MatchInfo
import com.dicoding.footy.domain.model.Lineup
import com.dicoding.footy.domain.model.PlayerInLineup
import com.dicoding.footy.domain.model.ShirtColor
import com.dicoding.footy.domain.model.Statistics

object DataMapper {
    fun teamSearchItemsToFavoriteTeamItems(teamSearchItems: List<TeamSearchItem>): List<FavoriteTeam> {
        return teamSearchItems.map {
            FavoriteTeam(
                id = it.team?.id ?: 0,
                badge = it.team?.logo ?: "",
                name = it.team?.name ?: "",
                country = it.team?.country ?: "",
            )
        }
    }

    fun fixturesItemsToMatchInfos(fixturesItems: List<FixturesItem>): List<MatchInfo> {
        return fixturesItems.map {
            MatchInfo(
                fixtureId = it.fixture?.id ?: 0,
                date = timestampToLocalDate(it.fixture?.timestamp?.toString() ?: "0", it.fixture?.timezone ?: "UTC"),
                time = timestampToLocalTime(it.fixture?.timestamp?.toString() ?: "0", it.fixture?.timezone ?: "UTC"),
                status = it.fixture?.status?.long ?: "Not Started",
                team1Id = it.teams?.home?.id ?: 0,
                team1Score = it.goals?.home ?: 0,
                team1Badge = it.teams?.home?.logo ?: "",
                team1Name = it.teams?.home?.name ?: "Team 1",
                team1Status = "Home",
                team2Id = it.teams?.away?.id ?: 0,
                team2Score = it.goals?.away ?: 0,
                team2Badge = it.teams?.away?.logo ?: "",
                team2Name = it.teams?.away?.name ?: "Team 2",
                team2Status = "Away",
                stadium = it.fixture?.venue?.name ?: "Stadium",
                competition = it.league?.name ?: "Competition",
            )
        }
    }

    fun eventsItemsToEvents(eventsItems: List<EventsItem>): List<Event> {
        return eventsItems.map {
            val type = it.type
            val detail = it.detail
            val eventType = if (type == "Goal") {
                EventType.GOAL
            } else if (type == "subst") {
                EventType.SUBSTITUTION
            } else if (type == "Card" && detail == "Yellow Card") {
                EventType.YELLOW_CARD
            } else if (type == "Card" && detail == "Red Card") {
                EventType.RED_CARD
            } else {
                EventType.GOAL
            }

            Event(
                time = it.time ?: Time(0, 0),
                type = eventType,
                teamId = it.team?.id ?: 0,
                player1Name = it.player?.name ?: "",
                player2Name = it.player?.name,
            )
        }
    }

    fun statisticsItemToStatistics(statisticsItem: List<StatisticsItem>): Statistics {
        return Statistics(
            shotsOnGoalHome = statisticsItem[0].statistics?.get(0)?.value?.toInt() ?: 0,
            shotsOnGoalAway = statisticsItem[1].statistics?.get(0)?.value?.toInt() ?: 0,
            totalShotsHome = statisticsItem[0].statistics?.get(2)?.value?.toInt() ?: 0,
            totalShotsAway = statisticsItem[1].statistics?.get(2)?.value?.toInt() ?: 0,
            ballPossessionHome = statisticsItem[0].statistics?.get(9)?.value?.dropLast(1)?.toInt() ?: 0,
            ballPossessionAway = statisticsItem[1].statistics?.get(9)?.value?.dropLast(1)?.toInt() ?: 0,
            foulsHome = statisticsItem[0].statistics?.get(6)?.value?.toInt() ?: 0,
            foulsAway = statisticsItem[1].statistics?.get(6)?.value?.toInt() ?: 0,
            passAccuracyHome = statisticsItem[0].statistics?.get(15)?.value?.dropLast(1)?.toInt() ?: 0,
            passAccuracyAway = statisticsItem[1].statistics?.get(15)?.value?.dropLast(1)?.toInt() ?: 0,
            expectedGoalsHome = statisticsItem[0].statistics?.get(16)?.value?.toDouble() ?: 0.0,
            expectedGoalsAway = statisticsItem[1].statistics?.get(16)?.value?.toDouble() ?: 0.0,
        )
    }

    fun lineupsItemToLineup(lineupsItem: List<LineupsItem>): List<Lineup> {
        return lineupsItem.map {
            val playersRows = arrayListOf<List<PlayerInLineup>>()
            val players = arrayListOf<PlayerInLineup>()
            var currentRow = 0

            it.startXI?.filterNotNull()?.forEach { startxi ->
                val row = startxi.player?.grid?.get(0)?.digitToInt() ?: currentRow
                if(currentRow != row) {
                    if (currentRow != 0) {
                        playersRows.add(players)
                    }
                    players.clear()
                    currentRow = row
                }
                players.add(
                    PlayerInLineup(
                        startxi.player?.name ?: "",
                        startxi.player?.number ?: 0,
                    )
                )
            }

            Lineup(
                teamId = it.team?.id ?: 0,
                formation = it.formation ?: "4-3-3",
                goalkeeperColor = ShirtColor(
                    it.team?.colors?.goalkeeper?.primary ?: "000000",
                    it.team?.colors?.goalkeeper?.number ?: "ffffff"
                ),
                outfieldColor = ShirtColor(
                    it.team?.colors?.player?.primary ?: "000000",
                    it.team?.colors?.player?.number ?: "ffffff"
                ),
                players = playersRows,
            )
        }
    }

    fun headToHeadItemsToMatchInfos(headToHeadItems: List<HeadToHeadItem>): List<MatchInfo> {
        return headToHeadItems.map {
            MatchInfo(
                fixtureId = it.fixture?.id ?: 0,
                date = timestampToLocalDate(it.fixture?.timestamp?.toString() ?: "0", it.fixture?.timezone ?: "UTC"),
                time = timestampToLocalTime(it.fixture?.timestamp?.toString() ?: "0", it.fixture?.timezone ?: "UTC"),
                status = it.fixture?.status?.short ?: "NS",
                team1Id = it.teams?.home?.id ?: 0,
                team1Score = it.goals?.home ?: 0,
                team1Badge = it.teams?.home?.logo ?: "",
                team1Name = it.teams?.home?.name ?: "Team 1",
                team1Status = "Home",
                team2Id = it.teams?.away?.id ?: 0,
                team2Score = it.goals?.away ?: 0,
                team2Badge = it.teams?.away?.logo ?: "",
                team2Name = it.teams?.away?.name ?: "Team 2",
                team2Status = "Away",
                stadium = it.fixture?.venue?.name ?: "Stadium",
                competition = it.league?.name ?: "Competition",
            )
        }
    }
}