package com.example.kaloricketabulkylite.ui.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.ui.navigation.Screen

@Composable
fun SearchScreen(
    navController: NavController,
    model: SearchViewModel = hiltViewModel()
) {
    Box() {
        Button(onClick = { navController.navigate(Screen.Detail.route) }) {
            Text(text = "-> Detail screen")
        }
    }
}