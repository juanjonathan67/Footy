package com.dicoding.footy.domain.model

data class MatchInfo (
    val id: Int?,
    val date: String?,
    val time: String?,
    val status: String?,
    val team1Score: String?,
    val team1Badge: String?,
    val team1Name: String?,
    val team1Status: String?,
    val team2Score: String?,
    val team2Badge: String?,
    val team2Name: String?,
    val team2Status: String?,
    val stadium: String?,
    val competition: String?,
)