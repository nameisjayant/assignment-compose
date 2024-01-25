package com.sujit.openinapp.feature.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sujit.openinapp.feature.main.bottombar.BottomBarRoute
import com.sujit.openinapp.feature.main.ui.screen.MainScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = "Main",
    ) {
        navigation(
            startDestination = BottomBarRoute.HOME.route,
            route = "Main"
        ) {
            composable(BottomBarRoute.HOME.route) {
                MainScreen()
            }
            composable(BottomBarRoute.Courses.route) {

            }
            composable(BottomBarRoute.Campaigns.route) {

            }
            composable(BottomBarRoute.Profile.route) {
            }
        }
    }
}