package com.example.kaloricketabulkylite.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun ScaffoldKalorickeTabulkyLite(
    modifier: Modifier = Modifier,
    topBarTitle: String,
    topBarActions: @Composable RowScope.() -> Unit = {},
    topBarDisplayNavigationIcon: Boolean,
    floatingActionButton: @Composable () -> Unit = {},
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    displayTopAppBar: Boolean = true,
    navController: NavController,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    backgroundColor: Color = KalorickeTabulkyLiteTheme.colors.background,
    content: @Composable (scaffoldState: ScaffoldState) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (displayTopAppBar)
                TopAppBarKalorickeTabulkyLite(
                    title = {
                        TopBarText(text = topBarTitle)
                    },
                    actions = {
                        topBarActions()
                    },
                    displayNavigationIcon = topBarDisplayNavigationIcon,
                    navigationIcon = { NavigationIconTopAppBarKalorickeTabulkyLite(navController) }
                )
        },
        floatingActionButton = floatingActionButton,
        drawerContent = drawerContent,
        snackbarHost = snackBarHost,
        modifier = modifier,
        backgroundColor = backgroundColor
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ConnectivityStatus()
            content(scaffoldState)
        }
    }
}