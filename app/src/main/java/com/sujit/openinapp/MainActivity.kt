package com.sujit.openinapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sujit.openinapp.feature.main.AppNavigation
import com.sujit.openinapp.feature.main.bottombar.BottomBarRoute
import com.sujit.openinapp.feature.main.bottombar.BottomBarScreen
import com.sujit.openinapp.feature.main.bottombar.BottomNavCurve
import com.sujit.openinapp.ui.theme.BackgroundColor
import com.sujit.openinapp.ui.theme.OpenInAppTheme
import dagger.hilt.android.AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberAppState()
            OpenInAppTheme {
                Surface(
                    modifier = Modifier.background(BackgroundColor).fillMaxSize(),
                ) {
                    Scaffold(
                        bottomBar = {
                            if (appState.shouldShowBottomBar)
                                BottomAppBar(
                                    modifier = Modifier.height(130.dp).clip(BottomNavCurve())
                                ) {
                                    BottomBarScreen(navHostController = appState.navHostController)
                                }
                        }
                    ) {  paddingValues ->
                        AppNavigation(
                            navHostController = appState.navHostController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController()
) = remember(navHostController) {
    AppState(navHostController)
}

@Stable
class AppState(
    val navHostController: NavHostController
) {

    private val routes = BottomBarRoute.entries.map { it.route }

    val shouldShowBottomBar: Boolean
        @Composable get() =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in routes

}
