package com.example.kaloricketabulkylite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
            KalorickeTabulkyLiteTheme {
                Navigation()
            }
        }
    }
}