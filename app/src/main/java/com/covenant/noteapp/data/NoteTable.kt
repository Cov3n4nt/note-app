package com.covenant.noteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date


@Entity(tableName = "note_table")
data class NoteTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "header")
    val header: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "date_created")
    val dateCreated: LocalDateTime,
    @ColumnInfo(name = "is_deleted")
    val isDeleted: Int,
)
