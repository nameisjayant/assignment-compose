package com.sujit.openinapp.feature.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sujit.openinapp.R
import com.sujit.openinapp.ui.theme.BackgroundColor
import com.sujit.openinapp.ui.theme.LightGrayColor


@Composable
fun ViewAnalyticsComponent(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.view_analytics),
    @DrawableRes icon: Int = R.drawable.view
) {

    Row(
        modifier = modifier
            .background(
                BackgroundColor,
                RoundedCornerShape(8.dp)
            )
            .border(1.dp, LightGrayColor, RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        AppIconComponent(icon = icon, modifier = Modifier.size(32.dp))
        SpacerWidth()
        Text(
            text = text, style = LocalTextStyle.current.copy(
                color = Color.Black,
                fontWeight = FontWeight.W600
            ),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}