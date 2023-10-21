package com.covenant.noteapp.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.covenant.noteapp.ui.theme.NoteAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField(
    label: String,
    textValue: String,
    onValueChange: (TextFieldValue) ->Unit,
    labelSize: TextUnit,
    labelWeight: FontWeight,
    labelFont: FontFamily,
    modifier: Modifier = Modifier,
    ){
    TextField(
        value = textValue,
        onValueChange = { newText ->
            onValueChange(TextFieldValue(text = newText))
        },
        textStyle = TextStyle(
            fontSize = labelSize,
            fontWeight = labelWeight,
            fontFamily = labelFont,
        ),
        modifier = modifier
            .fillMaxWidth(),
        placeholder = { Text(
            text = label,
            fontSize = labelSize,
            fontWeight = labelWeight
        )},
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        )

    )
}

@Preview
@Composable
fun TransparentTextFieldPrev() {
    var testVal by remember { mutableStateOf(TextFieldValue("")) }
    NoteAppTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()) {

            TransparentTextField(
                label = "Title",
                textValue = testVal.text,
                onValueChange = {NewBody -> testVal = NewBody},
                labelSize = 24.sp,
                labelWeight = FontWeight.Bold,
                labelFont = FontFamily.Monospace,
                modifier = Modifier
                    .fillMaxWidth()
            )

            TransparentTextField(
                label = "Body",
                textValue = testVal.text,
                onValueChange = {NewBody -> testVal = NewBody},
                labelSize = 15.sp,
                labelWeight = FontWeight.Normal,
                labelFont = FontFamily.Monospace,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

        }
    }

}