package com.dicoding.footy.data.remote.response.statistics

data class StatisticsResponse(
	val response: Response? = null,
	val get: String? = null,
	val paging: Paging? = null,
	val parameters: Parameters? = null,
	val results: Int? = null,
	val errors: List<Any?>? = null
)

data class Played(
	val total: Int? = null,
	val away: Int? = null,
	val home: Int? = null
)

data class JsonMember7690(
	val total: Any? = null,
	val percentage: Any? = null
)

data class Paging(
	val current: Int? = null,
	val total: Int? = null
)

data class Streak(
	val wins: Int? = null,
	val loses: Int? = null,
	val draws: Int? = null
)

data class Cards(
	val red: Red? = null,
	val yellow: Yellow? = null
)

data class Missed(
	val total: Int? = null,
	val percentage: String? = null
)

data class Parameters(
	val league: String? = null,
	val season: String? = null,
	val team: String? = null
)

data class JsonMember4660(
	val total: Any? = null,
	val percentage: Any? = null
)

data class Response(
	val failedToScore: FailedToScore? = null,
	val cards: Cards? = null,
	val form: String? = null,
	val biggest: Biggest? = null,
	val cleanSheet: CleanSheet? = null,
	val penalty: Penalty? = null,
	val league: League? = null,
	val team: Team? = null,
	val fixtures: Fixtures? = null,
	val goals: Goals? = null,
	val lineups: List<LineupsItem?>? = null
)

data class Yellow(
	val jsonMember: JsonMember? = null,
	val jsonMember106120: JsonMember106120? = null,
	val jsonMember4660: JsonMember4660? = null,
	val jsonMember3145: JsonMember3145? = null,
	val jsonMember7690: JsonMember7690? = null,
	val jsonMember015: JsonMember015? = null,
	val jsonMember91105: JsonMember91105? = null,
	val jsonMember1630: JsonMember1630? = null,
	val jsonMember6175: JsonMember6175? = null
)

data class Scored(
	val total: Int? = null,
	val percentage: String? = null
)

data class LineupsItem(
	val formation: String? = null,
	val played: Int? = null
)

data class JsonMember3145(
	val total: Any? = null,
	val percentage: Any? = null
)

data class JsonMember6175(
	val total: Any? = null,
	val percentage: Any? = null
)

data class Average(
	val total: String? = null,
	val away: String? = null,
	val home: String? = null
)

data class Total(
	val total: Int? = null,
	val away: Int? = null,
	val home: Int? = null
)

data class Minute(
	val jsonMember106120: JsonMember106120? = null,
	val jsonMember4660: JsonMember4660? = null,
	val jsonMember3145: JsonMember3145? = null,
	val jsonMember7690: JsonMember7690? = null,
	val jsonMember015: JsonMember015? = null,
	val jsonMember91105: JsonMember91105? = null,
	val jsonMember1630: JsonMember1630? = null,
	val jsonMember6175: JsonMember6175? = null
)

data class Loses(
	val total: Int? = null,
	val away: String? = null,
	val home: String? = null
)

data class Team(
	val name: String? = null,
	val logo: String? = null,
	val id: Int? = null
)

data class Biggest(
	val wins: Wins? = null,
	val loses: Loses? = null,
	val streak: Streak? = null,
	val goals: Goals? = null
)

data class CleanSheet(
	val total: Int? = null,
	val away: Int? = null,
	val home: Int? = null
)

data class JsonMember015(
	val total: Any? = null,
	val percentage: Any? = null
)

data class JsonMemberFor(
	val average: Average? = null,
	val total: Total? = null,
	val minute: Minute? = null,
	val away: Int? = null,
	val home: Int? = null
)

data class Fixtures(
	val wins: Wins? = null,
	val loses: Loses? = null,
	val draws: Draws? = null,
	val played: Played? = null
)

data class JsonMember91105(
	val total: Int? = null,
	val percentage: String? = null
)

data class Draws(
	val total: Int? = null,
	val away: Int? = null,
	val home: Int? = null
)

data class JsonMember106120(
	val total: Any? = null,
	val percentage: Any? = null
)

data class League(
	val country: String? = null,
	val flag: String? = null,
	val name: String? = null,
	val logo: String? = null,
	val season: Int? = null,
	val id: Int? = null
)

data class FailedToScore(
	val total: Int? = null,
	val away: Int? = null,
	val home: Int? = null
)

data class JsonMember1630(
	val total: Any? = null,
	val percentage: Any? = null
)

data class JsonMember(
	val total: Int? = null,
	val percentage: String? = null
)

data class Penalty(
	val total: Int? = null,
	val scored: Scored? = null,
	val missed: Missed? = null
)

data class Red(
	val jsonMember106120: JsonMember106120? = null,
	val jsonMember4660: JsonMember4660? = null,
	val jsonMember3145: JsonMember3145? = null,
	val jsonMember7690: JsonMember7690? = null,
	val jsonMember015: JsonMember015? = null,
	val jsonMember91105: JsonMember91105? = null,
	val jsonMember1630: JsonMember1630? = null,
	val jsonMember6175: JsonMember6175? = null
)

data class Against(
	val average: Average? = null,
	val total: Total? = null,
	val minute: Minute? = null,
	val away: Int? = null,
	val home: Int? = null
)

data class Goals(
	val against: Against? = null,
	val jsonMemberFor: JsonMemberFor? = null
)

data class Wins(
	val total: Int? = null,
	val away: String? = null,
	val home: String? = null
)

