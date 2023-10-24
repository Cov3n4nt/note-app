package com.covenant.noteapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.covenant.noteapp.data.NoteTable

@Composable
fun ListOtherNotes(
    notes: List<NoteTable>,
    label: String,
    labelSize: TextUnit,
    onClick: (Int)-> Unit,
    modifier: Modifier = Modifier,
) {
    if(notes.none{!it.isPinned}){
        Box(){

        }
    }
    else{
        Box(
            modifier = modifier,
        ) {
            LazyColumn {
                item {
                    Text(
                        text = label,
                        fontSize = labelSize,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp),
                    )
                }
                items(notes) { note ->
                    NoteCard(
                        header = note.header,
                        body = note.body,
                        date = note.dateCreated.toLocalDate(),
                        id = note.id,
                        onClick = {onClick(note.id)}
                    )
                }
            }
        }
    }


}