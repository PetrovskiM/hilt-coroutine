package com.mpdevelopment.hiltcoroutine.extension

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun LocalDateTime.getMillis(): Long =
    ZonedDateTime.of(this, ZoneId.systemDefault()).toInstant().toEpochMilli()