package com.dicoding.footy.domain.model

import com.dicoding.footy.data.remote.response.events.Time

data class Event (
    val time: Time,
    val type: EventType,
    val teamId: Int,
    val player1Name: String,
    val player2Name: String?,
)

enum class EventType {
    YELLOW_CARD,
    RED_CARD,
    GOAL,
    SUBSTITUTION,
}