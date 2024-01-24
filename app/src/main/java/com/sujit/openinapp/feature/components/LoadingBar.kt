package com.sujit.openinapp.feature.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sujit.openinapp.ui.theme.BlueColor


@Composable
fun LoadingBarComponent(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = BlueColor
    )
}