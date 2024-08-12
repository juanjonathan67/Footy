package com.dicoding.footy.data.remote.response.lineups

import com.google.gson.annotations.SerializedName

data class LineupsResponse(

	@field:SerializedName("response")
	val response: List<ResponseItem?>? = null,

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

data class Colors(

	@field:SerializedName("goalkeeper")
	val goalkeeper: Goalkeeper? = null,

	@field:SerializedName("player")
	val player: Player? = null
)

data class StartXIItem(

	@field:SerializedName("player")
	val player: Player? = null
)

data class SubstitutesItem(

	@field:SerializedName("player")
	val player: Player? = null
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

data class ResponseItem(

	@field:SerializedName("substitutes")
	val substitutes: List<SubstitutesItem?>? = null,

	@field:SerializedName("startXI")
	val startXI: List<StartXIItem?>? = null,

	@field:SerializedName("team")
	val team: Team? = null,

	@field:SerializedName("formation")
	val formation: String? = null,

	@field:SerializedName("coach")
	val coach: Coach? = null
)

data class Coach(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Goalkeeper(

	@field:SerializedName("border")
	val border: String? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("primary")
	val primary: String? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("colors")
	val colors: Colors? = null
)

data class Player(

	@field:SerializedName("border")
	val border: String? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("primary")
	val primary: String? = null,

	@field:SerializedName("pos")
	val pos: String? = null,

	@field:SerializedName("grid")
	val grid: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
