package com.covenant.noteapp.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.covenant.noteapp.data.Notes
import com.covenant.noteapp.ui.theme.NoteAppTheme
import com.covenant.noteapp.viewModel.NoteViewModel
import java.time.LocalDate
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavHostController, viewModel: NoteViewModel) {
    var header by remember { mutableStateOf(TextFieldValue("")) }
    var body by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        TopAppBar(
            title = { Text(text = "") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back arrow"
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    viewModel.addNote(
                        Notes(
                            id = UUID.randomUUID().toString(),
                            header = header.text,
                            body = body.text,
                            dateCreated = LocalDate.now()
                        )
                    )
                    navController.navigateUp()
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

        TextField(
            value = header,
            onValueChange = { newHeader ->
                header = newHeader
            },
            textStyle = TextStyle(fontFamily = FontFamily.Monospace),
            label = { Text(text = "Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .background(color = Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        TextField(
            value = body,
            onValueChange = { newHeader ->
                body = newHeader
            },
            textStyle = TextStyle(fontFamily = FontFamily.Monospace),
            label = { Text(text = "Body") },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
                .background(color = Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}
