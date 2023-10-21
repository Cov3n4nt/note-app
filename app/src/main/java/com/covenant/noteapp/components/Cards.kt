package com.covenant.noteapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.covenant.noteapp.ui.theme.NoteAppTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    header: String,
    body: String,
    date: LocalDate,
    onClick: () -> Unit,
    id: String,
    modifier: Modifier = Modifier,
) {
    OutlinedCard( colors = CardDefaults
        .cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(0.5.dp, Color.Gray),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,) {
            Text(text = header,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = date.toString(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(10.dp)
            )
        }

        Text(text = body,
            maxLines = 3,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(10.dp)
            )
    }
}

@Preview
@Composable
fun NoteCardPrev() {
    NoteAppTheme {
        NoteCard(header = "Header", body = "Body", onClick = { /*TODO*/ }, date = LocalDate.now(),
            id = "aaaa"
        )
    }
}