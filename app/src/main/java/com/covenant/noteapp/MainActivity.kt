package com.covenant.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.covenant.noteapp.data.NoteDatabase
import com.covenant.noteapp.data.NoteRepository
import com.covenant.noteapp.ui.theme.NoteAppTheme
import com.covenant.noteapp.viewmodel.NoteViewModel
import com.covenant.noteapp.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dao = NoteDatabase.getDatabase(application).noteDao()
            val repository = NoteRepository(dao)
            val factory = NoteViewModelFactory(dao)
            val viewModel: NoteViewModel = viewModel(factory = factory)
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel)
                }
            }
        }
    }
}



