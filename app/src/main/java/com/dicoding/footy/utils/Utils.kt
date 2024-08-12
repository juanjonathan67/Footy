package com.dicoding.footy.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("E, d MMM y")
val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("H:m")

fun timestampToInstant(timestamp: String): Instant = Instant.ofEpochSecond(timestamp.toLong())

fun instantToLocalDateTime(instant: Instant, zoneId: String): LocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(zoneId))

fun timestampToLocalDate(timestamp: String, zoneId: String): String = instantToLocalDateTime(timestampToInstant(timestamp), zoneId).format(dateFormat)

fun timestampToLocalTime(timestamp: String, zoneId: String): String = instantToLocalDateTime(timestampToInstant(timestamp), zoneId).format(timeFormat)