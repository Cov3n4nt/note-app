package com.covenant.noteapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.covenant.noteapp.components.ListOtherNotes
import com.covenant.noteapp.components.ListPinNotes
import com.covenant.noteapp.components.NoteCard
import com.covenant.noteapp.components.Scrawlo
import com.covenant.noteapp.components.SearchTextField
import com.covenant.noteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel
){
    val notes = viewModel.searchNotes(viewModel.search.text).collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchTextField(
                    search = viewModel.search.text,
                    onValueChange = { newSearch ->
                        viewModel.updateSearch(newSearch)
                    },
                )
                IconButton(onClick = { navController.navigate("archivedScreen") }) {
                    Icon(
                        imageVector = Icons.Outlined.Archive,
                        contentDescription = "Archive",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addNoteScreen")
                },
                modifier = Modifier.padding(16.dp),
                containerColor = MaterialTheme.colorScheme.inverseSurface

            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    )
    { 14.dp
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            ){
            item {
                if(notes.value.isEmpty()){
                    Scrawlo(
                        text = "Scrawlo couldn't find any notes here",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                    )
                }
                else{
                    ListPinNotes(
                        notes = notes.value.filter { it.isPinned },
                        onClick = {noteId ->
                            navController.navigate("editNoteScreen/$noteId")
                        },
                        label = "Pinned Notes",
                        labelSize = 15.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    ListOtherNotes(
                        notes = notes.value.filter { !it.isPinned },
                        label = "Notes",
                        labelSize = 15.sp,
                        modifier = Modifier.padding(8.dp),
                        onClick = {noteId ->
                            navController.navigate("editNoteScreen/${noteId}")
                        }
                    )
                }
            }
        }
    }
}

