package com.covenant.noteapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.covenant.noteapp.data.NoteTable

@Composable
fun ListOtherNotes(
    notes: List<NoteTable>,
    labelSize: TextUnit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pinnedNotes = remember(notes) { notes.filter { it.isPinned } }
    val unpinnedNotes = remember(notes) { notes.filter { !it.isPinned } }

    LazyColumn(modifier = modifier) {
        if (pinnedNotes.isNotEmpty()) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Pinned notes",
                        fontSize = labelSize,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp),
                    )
                    Icon(
                        imageVector = Icons.Default.Pets,
                        contentDescription = "Pin notes"
                    )
                }
            }
            items(pinnedNotes) { item ->
                NoteCard(
                    header = item.header,
                    body = item.body,
                    date = item.dateCreated.timeElapsedString(),
                    id = item.id,
                    onClick = { onClick(item.id) }
                )
            }
        }

        if (unpinnedNotes.isNotEmpty()) {
            item {
                Text(
                    text = "Notes",
                    fontSize = labelSize,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp),
                )
            }
            items(unpinnedNotes) { note ->
                NoteCard(
                    header = note.header,
                    body = note.body,
                    date = note.dateCreated.timeElapsedString(),
                    id = note.id,
                    onClick = { onClick(note.id) }
                )
            }
        }
    }
}