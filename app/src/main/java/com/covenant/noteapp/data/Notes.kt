package com.covenant.noteapp.data


import java.time.LocalDate

data class Notes(
    val id: String,
    val header: String,
    val body: String,
    val dateCreated: LocalDate,
    val isDeleted: Boolean,
)