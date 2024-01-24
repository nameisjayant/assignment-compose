package com.sujit.openinapp.feature.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sujit.openinapp.ui.theme.DarkGrayColor


@Composable
fun ViewReportRow(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    count: String,
    backgroundColor: Color
) {
    Column(
        modifier = modifier
            .padding(end = 16.dp)
            .background(
                Color.White,
                RoundedCornerShape(8.dp)
            )
            .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 24.dp)
    ) {
        CustomAppIcon(icon = icon, background = backgroundColor, cornerRadius = 50f) {}
        SpacerHeight(22.dp)
        Text(
            text = count, style = LocalTextStyle.current.copy(
                color = Color.Black,
                fontWeight = FontWeight.W600
            )
        )
        SpacerHeight(5.dp)
        Text(
            text = title, style = MaterialTheme.typography.labelLarge.copy(
                color = DarkGrayColor,
                fontWeight = FontWeight.W400
            )
        )
    }
}