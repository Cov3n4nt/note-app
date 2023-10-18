package com.covenant.noteapp.viewModel

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.ViewModel
import com.covenant.noteapp.data.Notes
import java.time.LocalDate
import java.time.LocalDateTime

class NoteViewModel: ViewModel() {

    //Make a list like this as a shared pref
    private var _notes = mutableListOf<Notes>()

    fun getNotes(): List<Notes>{
        return _notes
    }
    fun addNote(note: Notes){
        _notes.add(note)
    }

    fun removeNote(note: Notes){
        _notes.remove(note)
    }

    fun updateNote(note: Notes, header: String, body: String, date: LocalDate){

        val index = _notes.indexOf(note);

        _notes[index] = _notes[index].let{
            it.copy(
                id = it.id,
                header = header,
                body = body,
                dateCreated = date,
            )
        }


    }
}