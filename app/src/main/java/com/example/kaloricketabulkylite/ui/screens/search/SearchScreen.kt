package com.example.kaloricketabulkylite.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.R
import com.example.kaloricketabulkylite.ui.components.ScaffoldKalorickeTabulkyLite
import com.example.kaloricketabulkylite.ui.components.SpacerDefault
import com.example.kaloricketabulkylite.ui.components.SpacerSmall
import com.example.kaloricketabulkylite.ui.screens.search.composables.PotravinaCard
import com.example.kaloricketabulkylite.ui.screens.search.composables.SearchBar
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme
import com.example.kaloricketabulkylite.utils.data.Resource
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navController: NavController,
    model: SearchViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val searchResult = model.searchResult.collectAsState()

    ScaffoldKalorickeTabulkyLite(
        topBarTitle = stringResource(id = R.string.app_name),
        topBarDisplayNavigationIcon = false,
        navController = navController
    ) { scaffoldState ->
        Column(
            modifier = Modifier
        ) {
            SearchBar(
                modifier = Modifier.padding(horizontal = KalorickeTabulkyLiteTheme.paddings.defaultPadding),
                queryStateFlow = model.query,
                onQueryChange = { newValue: String ->
                    model.setNewQueryAndSearch(newValue)
                }
            )

            SpacerDefault()

            searchResult.value?.let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(KalorickeTabulkyLiteTheme.paddings.defaultPadding)
                        ) {
                            if (it.data?.potravina?.isNotEmpty() == true) {
                                items(it.data.potravina.sortedBy { it.energie.value }) { potravina ->
                                    PotravinaCard(
                                        modifier = Modifier.padding(horizontal = KalorickeTabulkyLiteTheme.paddings.defaultPadding),
                                        potravina = potravina,
                                        navController = navController,
                                        jednotka = it.data.jedn
                                    )
                                }

                                item {}
                            } else {
                                item {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "Pro zadaný výraz nebylo nic nalezeno.")
                                    }
                                }
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "Chyba při stahování dat :(",
                                    textAlign = TextAlign.Center
                                )
                                SpacerDefault()
                                Button(
                                    onClick = {
                                        scope.launch {
                                            model.search()
                                        }
                                    },
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = "Zkusit znovu".uppercase()
                                    )
                                }
                            }
                        }
                    }
                    Resource.Status.LOADING -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            } ?: run {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .heightIn(max = 200.dp)
                            .widthIn(max = 200.dp)
                            .fillMaxWidth(0.75f)
                            .align(Alignment.CenterHorizontally)
                    )

                    SpacerSmall()

                    Text(
                        text = "Můžete začít hledat! :)",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = KalorickeTabulkyLiteTheme.paddings.defaultPadding)
                    )
                }
            }
        }
    }
}