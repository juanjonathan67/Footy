package com.dicoding.footy.data.remote.response.teamSearch

import com.google.gson.annotations.SerializedName

data class TeamSearchResponse(

	@field:SerializedName("response")
	val response: List<TeamSearchItem?>? = null,

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

	@field:SerializedName("search")
	val search: String? = null
)

data class Venue(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("surface")
	val surface: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("capacity")
	val capacity: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class TeamSearchItem(

	@field:SerializedName("venue")
	val venue: Venue? = null,

	@field:SerializedName("team")
	val team: Team? = null
)

data class Team(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("founded")
	val founded: Int? = null,

	@field:SerializedName("national")
	val national: Boolean? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
