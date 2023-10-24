package com.covenant.noteapp.data

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): Instant? {
        return value?.let { Instant.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Instant?): String? {
        return date?.toString()
    }
}