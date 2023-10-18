package com.covenant.noteapp.data


import java.time.LocalDate
import java.time.LocalDateTime

data class Notes(
    val id: String,
    val header: String,
    val body: String,
    val dateCreated: LocalDate,
)