package com.example.kaloricketabulkylite.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun NavigationIconTopAppBarKalorickeTabulkyLite(navController: NavController) {
    IconButton(
        onClick = {
            navController.navigateUp()
        }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = KalorickeTabulkyLiteTheme.colors.onSurface
        )
    }
}