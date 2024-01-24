package com.sujit.openinapp.feature.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sujit.openinapp.R
import com.sujit.openinapp.ui.theme.LightGrayColor


@Composable
fun AppIconComponent(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    imageVector: ImageVector? = null,
    tint: Color = Color.Unspecified
) {
    if (icon != null)
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )
    else
        Icon(
            imageVector = imageVector!!,
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )
}

@Composable
fun CustomAppIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    background: Color = Color.White.copy(alpha = 0.3f),
    tint: Color = Color.Unspecified,
    cornerRadius: Float = 12f,
    showBorder: Boolean = false,
    onClick: () -> Unit
) {
    AppIconComponent(
        icon = icon, modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .drawBehind {
                if (showBorder)
                    drawRoundRect(
                        color = LightGrayColor,
                        style = Stroke(
                            width = 6f
                        ), cornerRadius = CornerRadius(cornerRadius)
                    )
                drawRoundRect(
                    color = background,
                    cornerRadius = CornerRadius(cornerRadius)
                )
            }
            .padding(6.dp)
            .size(24.dp),
        tint = tint
    )
}

@Composable
fun NetworkIconComponent(
    modifier: Modifier = Modifier,
    icon: String
) {
    if(icon.isNotEmpty())
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(icon)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.question),
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape)
    )
    else
        AppIconComponent(icon = R.drawable.question, modifier = Modifier.size(48.dp))
}