package com.dicoding.footy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MatchInfoEntity (
    @PrimaryKey
    val fixtureId: Int,
)