package com.example.kaloricketabulkylite.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.ui.navigation.Screen

@Composable
fun DetailScreen(
    navController: NavController,
    model: DetailViewModel = hiltViewModel()
) {
    Box() {
        Button(onClick = { navController.navigate(Screen.Search.route) }) {
            Text(text = "-> Search screen")
        }
    }
}