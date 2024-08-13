package com.dicoding.footy.data.remote.response.statistics

import com.google.gson.annotations.SerializedName

data class StatisticsResponse(

	@field:SerializedName("response")
	val response: List<StatisticsItem?>? = null,

	@field:SerializedName("get")
	val get: String? = null,

	@field:SerializedName("paging")
	val paging: Paging? = null,

	@field:SerializedName("parameters")
	val parameters: Parameters? = null,

	@field:SerializedName("results")
	val results: Int? = null,

	@field:SerializedName("errors")
	val errors: List<Any?>? = null
)

data class StatsItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class StatisticsItem(

	@field:SerializedName("team")
	val team: Team? = null,

	@field:SerializedName("statistics")
	val statistics: List<StatsItem?>? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Parameters(

	@field:SerializedName("fixture")
	val fixture: String? = null
)
