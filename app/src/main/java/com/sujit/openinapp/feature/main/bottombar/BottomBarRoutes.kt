package com.sujit.openinapp.feature.main.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sujit.openinapp.R

enum class BottomBarRoute(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    HOME("/links", R.string.links, R.drawable.links),
    Courses("/courses", R.string.courses, R.drawable.course),
    QR("/qr", R.string.qr, R.drawable.qr),
    Campaigns("/campaigns", R.string.campaign, R.drawable.campaigns),
    Profile("/profile", R.string.profile, R.drawable.profile)
}