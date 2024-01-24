package com.sujit.openinapp.feature.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.sujit.openinapp.ui.theme.GreenColor
import com.sujit.openinapp.ui.theme.LightGreenColor


@Composable
fun TabComponent(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.talk_with_us),
    @DrawableRes icon: Int = R.drawable.whats_app,
    backgroundColor: Color = LightGreenColor,
    borderColor: Color = GreenColor
) {

    Row(
        modifier = modifier
            .background(
                backgroundColor,
                RoundedCornerShape(8.dp)
            )
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
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