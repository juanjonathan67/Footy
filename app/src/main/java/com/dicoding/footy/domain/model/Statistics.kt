package com.dicoding.footy.domain.model

data class Statistics (
    val shotsOnGoalHome: Int,
    val shotsOnGoalAway: Int,
    val totalShotsHome: Int,
    val totalShotsAway: Int,
    val ballPossessionHome: Int,
    val ballPossessionAway: Int,
    val foulsHome: Int,
    val foulsAway: Int,
    val passAccuracyHome: Int,
    val passAccuracyAway: Int,
    val expectedGoalsHome: Double,
    val expectedGoalsAway: Double,
)