package com.covenant.noteapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covenant.noteapp.data.NoteDAO
import com.covenant.noteapp.data.NoteTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow


class NoteViewModel(private val dao: NoteDAO) : ViewModel() {

    val archivedNotes = dao.getArchivedNotes()
    val notes = dao.getNotes()

    fun searchNotes(search:String): Flow<List<NoteTable>> {
        return dao.searchNotes(search)
    }
    fun addNote(notes: NoteTable){
        viewModelScope.launch(Dispatchers.IO){
            dao.insertNote(notes)
        }
    }
  fun deleteNote(notes: NoteTable){
      viewModelScope.launch(Dispatchers.IO){
          dao.deleteNote(notes)
      }
  }

    fun updateNote(notes: NoteTable){
        viewModelScope.launch(Dispatchers.IO){
            dao.updateNote(notes)
        }
    }

}
