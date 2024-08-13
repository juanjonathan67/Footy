package com.dicoding.footy.domain.model

data class Lineup (
    val teamId: Int,
    val formation: String,
    val goalkeeperColor: ShirtColor,
    val outfieldColor: ShirtColor,
    val players: List<List<PlayerInLineup>>
)

data class PlayerInLineup (
    val name: String,
    val number: Int,
)

data class ShirtColor(
    val shirtColor: String,
    val numberColor: String,
)