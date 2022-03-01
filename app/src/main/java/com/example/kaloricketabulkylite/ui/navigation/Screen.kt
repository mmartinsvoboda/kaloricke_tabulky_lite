package com.example.kaloricketabulkylite.ui.navigation

import com.example.kaloricketabulkylite.R

sealed class Screen(
    val route: String,
    var title: Int
) {
    object Search : Screen("searchScreen", R.string.search)
    object Detail : Screen("detailScreen", R.string.detail)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}