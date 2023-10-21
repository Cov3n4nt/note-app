package com.covenant.noteapp.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.covenant.noteapp.ui.theme.NoteAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    search: String,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .padding(4.dp)
        .clip(CircleShape)
    ){
        TextField(
            value = search,
            onValueChange = {newText ->
            onValueChange(TextFieldValue(text = newText))
            },
            textStyle = TextStyle(fontFamily = FontFamily.Monospace),
            placeholder = { Text(
                text = "Search",
                fontFamily = FontFamily.Monospace
            )},
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                )
            },
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun SearchTextFieldPrev() {
    var testVal by remember { mutableStateOf(TextFieldValue("")) }
    NoteAppTheme {
        SearchTextField(search = testVal.text, onValueChange = {NewSearch ->
            testVal = NewSearch})
    }
}