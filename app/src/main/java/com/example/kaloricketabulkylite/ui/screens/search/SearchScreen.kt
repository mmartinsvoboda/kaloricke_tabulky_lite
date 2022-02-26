package com.example.kaloricketabulkylite.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.R
import com.example.kaloricketabulkylite.ui.components.ScaffoldKalorickeTabulkyLite
import com.example.kaloricketabulkylite.ui.components.SpacerSmall
import com.example.kaloricketabulkylite.ui.navigation.Screen
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme
import com.example.kaloricketabulkylite.utils.data.Resource
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navController: NavController,
    model: SearchViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val query = model.query.collectAsState()
    val searchResult = model.searchResult.collectAsState()

    ScaffoldKalorickeTabulkyLite(
        topBarTitle = stringResource(id = R.string.app_name),
        topBarDisplayNavigationIcon = false,
        navController = navController
    ) { scaffoldState ->
        Column(
            modifier = Modifier.padding(horizontal = KalorickeTabulkyLiteTheme.paddings.defaultPadding)
        ) {
            Row {
                OutlinedTextField(
                    value = query.value,
                    onValueChange = { newValue: String ->
                        model.setQuery(newValue)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    placeholder = {
                        Text(text = "Hledat")
                    }
                )

                SpacerSmall()

                IconButton(
                    onClick = {
                        scope.launch {
                            model.search()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }

            searchResult.value.let {
                if (it != null) {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(KalorickeTabulkyLiteTheme.paddings.defaultPadding)
                            ) {
                                item {
                                    it.data?.potravina?.size?.let {
                                        Text(
                                            text = it.toString()
                                        )
                                    }
                                }

                                if (it.data?.potravina?.isNotEmpty() == true) {
                                    items(it.data.potravina) {
                                        Text(
                                            text = it.nazev,
                                            modifier = Modifier.clickable {
                                                navController.navigate(
                                                    Screen.Detail.withArgs(it.guidPotravina)
                                                )
                                            }
                                        )
                                    }
                                } else {
                                    item {
                                        Text(text = "Pro výraz ${query.value} nebylo nic nalezeno")
                                    }
                                }
                            }
                        }
                        Resource.Status.ERROR -> {
                            Text(text = "Nenalezen žádný záznam")
                        }
                        Resource.Status.LOADING -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(KalorickeTabulkyLiteTheme.paddings.defaultPadding)
                                )
                            }
                        }
                    }
                } else {
                    Text(text = "Můžete začít vyhledávat")
                }
            }
        }
    }
}