package com.covenant.noteapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.covenant.noteapp.data.NoteTable

@Composable
fun ListPinNotes(
    notes: List<NoteTable>,
    label: String,
    labelSize: TextUnit,
    onClick: (Int)-> Unit,
    modifier: Modifier = Modifier,
) {
    if(notes.none { it.isPinned }){
        Box(){

        }
    }
    else{
        Box(
            modifier = modifier,
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = label,
                        fontSize = labelSize,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp),
                    )
                    Icon(
                        imageVector = Icons.Default.Pets,
                        contentDescription = "Pin notes")
                }

                LazyRow(
                    content = {
                        items(notes) { item ->
                            NoteCard(
                                header = item.header,
                                body = item.body,
                                date = item.dateCreated.toLocalDate(),
                                id = item.id,
                                onClick = {onClick(item.id)}
                            )
                        }
                    }
                )


            }
        }
    }


}