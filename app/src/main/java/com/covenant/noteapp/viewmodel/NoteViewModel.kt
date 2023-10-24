package com.covenant.noteapp.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
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


    var header by mutableStateOf(TextFieldValue(""))
        private set
    var body by mutableStateOf(TextFieldValue(""))
        private set
    var search by mutableStateOf(TextFieldValue(""))
        private set
    var pin by mutableStateOf(Boolean)
        private set

    fun updateSearch(input: TextFieldValue){
        search = input
    }
    fun updatePin(input: Boolean.Companion){
        pin = input
    }

    fun updateHeader(input: TextFieldValue) {
        header = input
    }
    fun updateBody(input: TextFieldValue) {
        body = input
    }
    fun searchArchivedNotes(search:String): Flow<List<NoteTable>> {
        return dao.searchArchivedNotes(search)
    }
    fun clearContents(){
        body = TextFieldValue("")
        header = TextFieldValue("")
        search = TextFieldValue("")
    }
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
