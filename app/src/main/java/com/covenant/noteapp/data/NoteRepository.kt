package com.covenant.noteapp.data

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.material3.rememberTopAppBarState
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDAO) {

    suspend fun insertNote(note: NoteTable){
        noteDao.insertNote(note)
    }

//Implement is_delete update to 1 here:

    suspend fun archiveNote(note: NoteTable){
        noteDao.archiveNote(note)
    }

    suspend fun updateNote(note: NoteTable){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteTable){
        noteDao.deleteNote(note)
    }

    suspend fun getArchivedNotes(): Flow<List<NoteTable>>{
        return noteDao.getArchivedNotes()
    }

    suspend fun getNotes(): Flow<List<NoteTable>>{
        return noteDao.getNotes()
    }
}