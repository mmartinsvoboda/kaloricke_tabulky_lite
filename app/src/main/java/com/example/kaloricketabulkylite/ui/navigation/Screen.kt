package com.example.kaloricketabulkylite.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kaloricketabulkylite.R

sealed class Screen(
    val route: String,
    var title: Int
) {
    object Search : Screen("searchScreen", R.string.search)
    object Detail : Screen("detailScreen", R.string.detail)
}