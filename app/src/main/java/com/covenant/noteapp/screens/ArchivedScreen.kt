package com.covenant.noteapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.covenant.noteapp.components.ListOtherNotes
import com.covenant.noteapp.components.NoteCard
import com.covenant.noteapp.components.Scrawlo
import com.covenant.noteapp.components.SearchTextField
import com.covenant.noteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchivedScreen(navController: NavHostController, viewModel: NoteViewModel) {
    val notes = viewModel.searchArchivedNotes(viewModel.search.text).collectAsState(initial = emptyList())
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
                IconButton(onClick = {
                    viewModel.clearContents()
                    navController.navigateUp()
                }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Archive",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        },

    ) { innerPadding ->
        if(notes.value.isEmpty()){
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                content = {
                    item {
                        Scrawlo(
                            text = "Scrawlo couldn't find any notes here",
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                        )
                    }
                }
            )
        }
        else{
            ListOtherNotes(
                notes = notes.value,
                modifier = Modifier.padding(innerPadding)
                    .padding(8.dp),
                label = "Archived Notes",
                labelSize = 15.sp,
                onClick = {noteId ->
                    navController.navigate("editArchivedScreen/${noteId}")
                })
        }

    }
}

