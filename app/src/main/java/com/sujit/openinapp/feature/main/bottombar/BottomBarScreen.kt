package com.sujit.openinapp.feature.main.bottombar

import android.graphics.PointF
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sujit.openinapp.feature.components.AppIconComponent
import com.sujit.openinapp.feature.components.SpacerHeight
import com.sujit.openinapp.ui.theme.DarkGrayColor

@Composable
fun BottomBarScreen(
    navHostController: NavHostController
) {
    val tabs = listOf(
        BottomBarRoute.HOME,
        BottomBarRoute.Courses,
        BottomBarRoute.QR,
        BottomBarRoute.Campaigns,
        BottomBarRoute.Profile
    )

    val navStackBackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach {
            BottomBarRow(
                tab = it,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun BottomBarRow(
    modifier: Modifier = Modifier,
    tab: BottomBarRoute,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {

    val selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true
    val color = if (selected) Color.Black else DarkGrayColor


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            if (tab.route == BottomBarRoute.QR.route) {
            } else {
                navHostController.navigate(tab.route) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) {

        AppIconComponent(
            icon = tab.icon, modifier = Modifier.size(
                if(tab.route == BottomBarRoute.QR.route) 95.dp else 32.dp
            ).offset(
                x =if (tab.route == BottomBarRoute.QR.route) (8.dp) else 0.dp,
                y = if (tab.route == BottomBarRoute.QR.route) -(10.dp) else 0.dp
            ), tint = if (tab.route == BottomBarRoute.QR.route) Color.Unspecified else  color
        )
        if (tab.route != BottomBarRoute.QR.route) {
            SpacerHeight()
            Text(
                text = stringResource(id = tab.title),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.W600,
                    color = color
                )
            )
        }

    }
}

private const val CURVE_CIRCLE_RADIUS = 70


// the coordinates of the first curve
private val mFirstCurveStartPoint = PointF()
private val mFirstCurveControlPoint1 = PointF()
private val mFirstCurveControlPoint2 = PointF()
private val mFirstCurveEndPoint = PointF()

// the coordinates of the second curve
private val mSecondCurveControlPoint1 = PointF()
private val mSecondCurveControlPoint2 = PointF()
private var mSecondCurveStartPoint = PointF()
private var mSecondCurveEndPoint = PointF()

class BottomNavCurve : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply {
            val curveDepth = CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4F)


            // the coordinates (x,y) of the start point before curve
            mFirstCurveStartPoint.set(
                (size.width / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3),
                curveDepth
            )

            // the coordinates (x,y) of the end point after curve
            mFirstCurveEndPoint.set(
                size.width / 2,
                0F
            )

            // same thing for the second curve
            mSecondCurveStartPoint = mFirstCurveEndPoint
            mSecondCurveEndPoint.set(
                (size.width / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3),
                curveDepth
            )

            // the coordinates (x,y)  of the 1st control point on a cubic curve
            mFirstCurveControlPoint1.set(
                mFirstCurveStartPoint.x + curveDepth,
                mFirstCurveStartPoint.y
            )

            // the coordinates (x,y)  of the 2nd control point on a cubic curve
            mFirstCurveControlPoint2.set(
                mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS,
                mFirstCurveEndPoint.y
            )
            mSecondCurveControlPoint1.set(
                mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS,
                mSecondCurveStartPoint.y
            )
            mSecondCurveControlPoint2.set(
                mSecondCurveEndPoint.x - (curveDepth),
                mSecondCurveEndPoint.y
            )

            moveTo(0f, curveDepth)
            lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y)

            cubicTo(
                mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y
            )
            cubicTo(
                mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y
            )
            lineTo(size.width, curveDepth)
            lineTo(size.width, size.height)
            lineTo(0F, size.height)
        })
    }
}