package com.example.kaloricketabulkylite.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.example.kaloricketabulkylite.ui.theme.themes.DarkTheme
import com.example.kaloricketabulkylite.ui.theme.themes.LightTheme
import cz.is4uma.moje_studium.ui.theme.attr.*

@Composable
fun KalorickeTabulkyLiteTheme(
    content: @Composable () -> Unit
) {

    val colors = if (isSystemInDarkTheme()) {
        DarkTheme.DarkPalette
    } else {
        LightTheme.LightPalette
    }

    CompositionLocalProvider(
        LocalPaddings provides Paddings(),
        LocalElevations provides Elevations(card = 8.dp),
        LocalDarkMode provides DarkMode(isSystemInDarkTheme())
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography(),
            shapes = androidx.compose.material.Shapes(),
            content = content
        )
    }
}

object KalorickeTabulkyLiteTheme {
    /**
     * Proxy to [MaterialTheme]
     */
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    /**
     * Proxy to [MaterialTheme]
     */
    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    /**
     * Proxy to [MaterialTheme]
     */
    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    /**
     * Retrieves the current [Paddings].
     */
    val paddings: Paddings
        @Composable
        get() = LocalPaddings.current

    /**
     * Retrieves the current [Paddings].
     */
    val elevations: Elevations
        @Composable
        get() = LocalElevations.current

    /**
     * Retrieves the current dark mode state
     */
    val darkModeState: DarkMode
        @Composable
        get() = LocalDarkMode.current
}