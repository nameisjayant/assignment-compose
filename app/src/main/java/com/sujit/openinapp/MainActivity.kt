package com.sujit.openinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import com.sujit.openinapp.feature.components.HeaderComponent
import com.sujit.openinapp.feature.main.ui.screen.MainScreen
import com.sujit.openinapp.ui.theme.OpenInAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenInAppTheme {
               Scaffold {
                   MainScreen()
               }
            }
        }
    }
}
