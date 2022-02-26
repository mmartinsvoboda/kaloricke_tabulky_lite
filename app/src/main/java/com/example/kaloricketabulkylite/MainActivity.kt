package com.example.kaloricketabulkylite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.kaloricketabulkylite.ui.navigation.Navigation
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // install splash screen
        val splashScreen = installSplashScreen()

        setContent {
            KalorickeTabulkyLiteTheme(
                lightColorPalette = KalorickeTabulkyLiteTheme.colors,
                darkColorPalette = KalorickeTabulkyLiteTheme.colors
            ) {
                Navigation()
            }
        }
    }
}