package com.sujit.openinapp.feature.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sujit.openinapp.R
import com.sujit.openinapp.data.model.RecentLinks
import com.sujit.openinapp.ui.theme.BlueColor
import com.sujit.openinapp.ui.theme.DarkGrayColor
import com.sujit.openinapp.ui.theme.LightBlue100
import com.sujit.openinapp.ui.theme.LightBlue200


@Composable
fun LinkEachRow(
    modifier: Modifier = Modifier,
    data: RecentLinks
) {
    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            NetworkIconComponent(icon = data.original_image ?: "", modifier = Modifier.size(48.dp))
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = data.title ?: "-",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.W400,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                SpacerHeight(5.dp)
                Text(
                    text = data.times_ago ?: "-",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = DarkGrayColor,
                        fontWeight = FontWeight.W400

                    ),
                )
            }
            Column {
                Text(
                    text = data.total_clicks ?: "-",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.W400

                    ),
                )
                SpacerHeight(5.dp)
                Text(
                    text = stringResource(R.string.clicks),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = DarkGrayColor,
                        fontWeight = FontWeight.W400

                    ),
                )
            }
        }

        DashedLineRectangle {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        clipboardManager.setText(AnnotatedString(data.web_link ?: ""))
                    }
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = data.web_link ?: "-",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = BlueColor,
                        fontWeight = FontWeight.W400,
                    ),
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                AppIconComponent(icon = R.drawable.copy)
            }
        }
    }
}

@Composable
fun DashedLineRectangle(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .background(LightBlue100)
            .height(50.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 2.dp.toPx()
            val dashWidth = 5.dp.toPx()
            val dashGap = 2.dp.toPx()

            drawRoundRect(
                color = LightBlue200,
                size = size.copy(
                    width = size.width - strokeWidth,
                    height = size.height - strokeWidth
                ),
                style = Stroke(
                    width = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(dashWidth, dashGap), 10f
                    )
                ),
                cornerRadius = CornerRadius(8f)
            )
        }
        content()
    }
}