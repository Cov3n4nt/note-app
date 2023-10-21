package com.covenant.noteapp.viewModel


import androidx.lifecycle.ViewModel
import com.covenant.noteapp.data.Notes
import java.time.LocalDate


class NoteViewModel: ViewModel() {

    //Make a list like this as a shared pref
    private var _notes = mutableListOf<Notes>()

    fun getNotes(): List<Notes>{
        return _notes.filter { !it.isDeleted }
    }

    fun getDeletedNotes(): List<Notes>{

        val deletedNotes = _notes.filter { it.isDeleted }

        return deletedNotes
    }

    fun addNote(note: Notes){
        _notes.add(note)
    }

    fun removeNote(note: Notes){
        _notes.remove(note)
    }

    fun deleteNote(note:Notes){

        val index = _notes.indexOf(note)

        _notes[index] = _notes[index].let{
            it.copy(
                id = it.id,
                isDeleted = true
            )
        }

    }

    fun updateNote(note: Notes, header: String, body: String, date: LocalDate, isDeleted: Boolean){

        val index = _notes.indexOf(note)

        _notes[index] = _notes[index].let{
            it.copy(
                id = it.id,
                header = header,
                body = body,
                dateCreated = date,
                isDeleted = isDeleted,
            )
        }


    }
}