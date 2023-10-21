package com.covenant.noteapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.covenant.noteapp.ui.theme.NoteAppTheme

@Composable
fun Scrawlo(
    text: String,
    modifier: Modifier = Modifier,
) {
        Box(modifier = modifier,){
            Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Icon(
                    imageVector = Icons.Default.Pets,
                    contentDescription = "No notes found",
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }



}

@Preview
@Composable
fun ScrawloPrev() {
    NoteAppTheme {
        Scrawlo(text = "Scrawlo didn't find any notes here")   
    }
}