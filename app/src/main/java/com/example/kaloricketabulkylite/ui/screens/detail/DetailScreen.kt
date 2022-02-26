package com.example.kaloricketabulkylite.ui.screens.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.ui.components.DataLoader
import com.example.kaloricketabulkylite.ui.components.ScaffoldKalorickeTabulkyLite

@Composable
fun DetailScreen(
    guidPotravina: String,
    navController: NavController,
    model: DetailViewModel = hiltViewModel()
) {
    val title = remember {
        mutableStateOf("")
    }

    ScaffoldKalorickeTabulkyLite(
        topBarTitle = title.value,
        topBarDisplayNavigationIcon = true,
        navController = navController
    ) { scaffoldState ->

        DataLoader(
            resourceFlow = model.potravinaRepository.getPotravina(guidPotravina),
            scaffoldState = scaffoldState,
            navController = navController,
            noDataContent = {
                Text(text = "Potravina nebyla nalezena.")
            }
        ) {
            title.value = it?.nazev ?: "?"
            Text(text = it.toString())
        }
    }
}