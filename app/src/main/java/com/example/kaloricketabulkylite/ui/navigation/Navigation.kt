package com.example.kaloricketabulkylite.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kaloricketabulkylite.ui.screens.detail.DetailScreen
import com.example.kaloricketabulkylite.ui.screens.search.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        addSearchScreen(navController)
        addDetailScreen(navController)
    }
}

private fun NavGraphBuilder.addSearchScreen(
    navController: NavController
) {
    composable(Screen.Search.route) {
        SearchScreen(navController = navController)
    }
}

private fun NavGraphBuilder.addDetailScreen(
    navController: NavController
) {
    composable(Screen.Detail.withArgs("{guid_potravina}")) { backStackEntry ->
        val guidPotravina = backStackEntry.arguments?.getString("guid_potravina")!!

        DetailScreen(guidPotravina = guidPotravina, navController = navController)
    }
}