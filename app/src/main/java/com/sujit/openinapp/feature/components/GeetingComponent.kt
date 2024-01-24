package com.sujit.openinapp.feature.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sujit.openinapp.R
import com.sujit.openinapp.ui.theme.DarkGrayColor


@Composable
fun GreetingComponent(
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.good_morning), style = LocalTextStyle.current.copy(
                color = DarkGrayColor,
                fontWeight = FontWeight.W400
            )
        )
        SpacerHeight()
        Text(
            text = stringResource(R.string.sujit_das),
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.W600
            ),
        )

    }

}