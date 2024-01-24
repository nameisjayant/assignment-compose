package com.sujit.openinapp.feature.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sujit.openinapp.ui.theme.BlueColor
import com.sujit.openinapp.ui.theme.DarkGrayColor


@Composable
fun ToggleChipComponent(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean,
    index: Int,
    containerColor: Color = if (selected) BlueColor else Color.Transparent,
    contentColor: Color = if (selected) Color.White else DarkGrayColor,
    onValueChange: (Int) -> Unit
) {

    Button(
        onClick = {
            onValueChange(index)
        },
        modifier = modifier.padding(end = 24.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        contentPadding = PaddingValues(vertical = 6.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title, style = androidx.compose.material3.LocalTextStyle.current.copy(
                fontWeight = FontWeight.W600
            ),
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}