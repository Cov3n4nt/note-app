package com.covenant.noteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.covenant.noteapp.components.TransparentTextField
import com.covenant.noteapp.data.NoteTable
import com.covenant.noteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditArchivedScreen(navController: NavHostController, viewModel: NoteViewModel, noteId: Int) {

    val archivedNotes = viewModel.archivedNotes.collectAsState(initial = emptyList())
    val note = archivedNotes.value.find { it.id == noteId }
    note?.let {
        var header by remember { mutableStateOf(TextFieldValue(note.header)) }
        var body by remember { mutableStateOf(TextFieldValue(note.body)) }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back arrow"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.deleteNote(it)
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete Note"
                        )
                    }
                    IconButton(onClick = {
                       viewModel.updateNote(
                           NoteTable(
                               it.id,
                               header.text,
                               body.text,
                               it.dateCreated,
                               false
                           )
                       )
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Redo,
                            contentDescription = "Restore Note"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            TransparentTextField(
                label = "Title",
                textValue = header.text,
                onValueChange = { newHeader ->
                    header = newHeader},
                labelSize = 24.sp,
                labelWeight = FontWeight.SemiBold,
                labelFont = FontFamily.Monospace,
            )
            TransparentTextField(
                label = "Note",
                textValue = body.text,
                onValueChange = { newHeader ->
                    body = newHeader},
                labelSize = 15.sp,
                labelWeight = FontWeight.Normal,
                labelFont = FontFamily.Monospace,
            )
        }
    }
}
