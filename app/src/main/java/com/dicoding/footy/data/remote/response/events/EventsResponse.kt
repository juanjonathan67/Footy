package com.dicoding.footy.data.remote.response.events

import com.google.gson.annotations.SerializedName

data class EventsResponse(

	@field:SerializedName("response")
	val response: List<EventsItem?>? = null,

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

data class Parameters(

	@field:SerializedName("fixture")
	val fixture: String? = null
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

data class Time(

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("extra")
	val extra: Int? = null
)

data class Assist(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class EventsItem(

	@field:SerializedName("comments")
	val comments: String? = null,

	@field:SerializedName("assist")
	val assist: Assist? = null,

	@field:SerializedName("time")
	val time: Time? = null,

	@field:SerializedName("team")
	val team: Team? = null,

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("player")
	val player: Player? = null
)

data class Player(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
