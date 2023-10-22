package com.covenant.noteapp.data


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class NoteViewModel(private val dao: NoteDAO) : ViewModel() {
    //private val readAllData: LiveData<List<Notes>>
    //val noteList = repository.getResults()
//    init{
//        val noteDao = NoteDatabase.getDatabase(application).noteDao()
//        //repository = NoteRepository(noteDao)
//        //readAllData = repository.readAllData
//    }

    fun addNote(notes: NoteTable){
        viewModelScope.launch(Dispatchers.IO){
            dao.insertNote(notes)
        }
    }


//    fun getNotes(): List<Notes>{
//        return _notes.filter { !it.isDeleted }
//    }
}


//Original Viewmodel:

//class NoteViewModel: ViewModel() {
//
//    //Make a list like this as a shared pref
//    private var _notes = mutableListOf<Notes>()
//
//    fun getNotes(): List<Notes>{
//        return _notes.filter { !it.isDeleted }
//    }
//
//    fun getDeletedNotes(): List<Notes>{
//
//        val deletedNotes = _notes.filter { it.isDeleted }
//
//        return deletedNotes
//    }
//
//    fun addNote(note: Notes){
//        _notes.add(note)
//    }
//
//    fun removeNote(note: Notes){
//        _notes.remove(note)
//    }
//
//    fun deleteNote(note:Notes){
//
//        val index = _notes.indexOf(note)
//
//        _notes[index] = _notes[index].let{
//            it.copy(
//                id = it.id,
//                isDeleted = true
//            )
//        }
//
//    }
//
//    fun updateNote(note: Notes, header: String, body: String, date: LocalDate, isDeleted: Boolean){
//
//        val index = _notes.indexOf(note)
//
//        _notes[index] = _notes[index].let{
//            it.copy(
//                id = it.id,
//                header = header,
//                body = body,
//                dateCreated = date,
//                isDeleted = isDeleted,
//            )
//        }
//
//
//    }
//}