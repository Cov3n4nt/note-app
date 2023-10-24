package com.covenant.noteapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
fun EditNoteScreen(navController: NavHostController, viewModel: NoteViewModel, noteId: Int) {
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    val note = notes.value.find { it.id == noteId }
    note?.let {

        var header by remember { mutableStateOf(TextFieldValue(note.header)) }
        var body by remember { mutableStateOf(TextFieldValue(note.body)) }


        Column {
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
                        viewModel.updateNote(
                            NoteTable(
                                it.id,
                                header.text,
                                body.text,
                                it.dateCreated,
                                true,
                                false,
                            )
                        )
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete Note"
                        )
                    }

                    val isPinned = it.isPinned
                    val icon = if (isPinned) Icons.Default.PushPin else Icons.Outlined.PushPin
                    IconButton(
                        onClick = {
                            viewModel.updateNote(
                                NoteTable(
                                    it.id,
                                    header.text,
                                    body.text,
                                    it.dateCreated,
                                    isDeleted = false,
                                    isPinned = !isPinned,
                                )
                            )
                        }
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Save Note"
                        )
                    }
                    IconButton(onClick = {
                        viewModel.updateNote(
                            NoteTable(
                                it.id,
                                header.text,
                                body.text,
                                it.dateCreated,
                                isDeleted = false,
                                it.isPinned,
                            )
                        )
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Save Note"
                        )
                    }

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                content = {
                    item {
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
                            label = "Title",
                            textValue = body.text,
                            onValueChange = { newHeader ->
                                body = newHeader},
                            labelSize = 15.sp,
                            labelWeight = FontWeight.Normal,
                            labelFont = FontFamily.Monospace,
                        )
                    }
                }
            )
        }
    }
}
