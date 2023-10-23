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
import androidx.navigation.NavHostController
import com.covenant.noteapp.components.NoteCard
import com.covenant.noteapp.components.Scrawlo
import com.covenant.noteapp.components.SearchTextField
import com.covenant.noteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchivedScreen(navController: NavHostController, viewModel: NoteViewModel) {
    val archivedNotes = viewModel.archivedNotes.collectAsState(initial = emptyList())
    var searchArchive by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchTextField(
                    search = searchArchive.text,
                    onValueChange = { newSearch ->
                        searchArchive = newSearch
                    },

                    )
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Archive",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        },

    ) { innerPadding ->
        if(archivedNotes.value.isEmpty()){
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
            LazyColumn(
                modifier = Modifier.padding(innerPadding), // Apply padding from innerPadding
                content = {
                    items(archivedNotes.value) { item ->
                        NoteCard(
                            header = item.header,
                            body = item.body,
                            date = item.dateCreated.toLocalDate(),
                            id = item.id,
                            modifier = Modifier.padding(4.dp),
                            onClick = { navController.navigate("editArchivedScreen/${item.id}") }
                        )
                    }
                }
            )
        }

    }
}

