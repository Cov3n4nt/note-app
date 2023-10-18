package com.covenant.noteapp

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.covenant.noteapp.screens.AddNoteScreen
import com.covenant.noteapp.screens.EditNoteScreen
import com.covenant.noteapp.screens.NoteScreen
import com.covenant.noteapp.viewModel.NoteViewModel

@Composable
fun AppNavigation(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "noteScreen") {
        composable("noteScreen") {
            NoteScreen(navController, viewModel)
        }
        composable("addNoteScreen") {
            AddNoteScreen(navController,viewModel)
        }
        composable(
            route = "editNoteScreen/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val noteId = arguments.getString("noteId")
            EditNoteScreen(navController, viewModel, noteId!!)
        }
    }
}


