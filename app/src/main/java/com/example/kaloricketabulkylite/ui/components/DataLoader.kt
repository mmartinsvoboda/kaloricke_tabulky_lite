/*
 * Created by  Martin Svoboda
 * Copyright (c) IS4U s.r.o. 2021. All rights reserved.
 * Last modified 23.02.22 21:33
 */

package com.example.kaloricketabulkylite.ui.components

import android.accounts.AuthenticatorException
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme
import com.example.kaloricketabulkylite.utils.data.Resource
import com.example.kaloricketabulkylite.utils.data.UnsuccessfulCallException
import com.example.kaloricketabulkylite.utils.data.isDataNullOrEmptyList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.io.EOFException
import java.net.UnknownHostException

@Composable
fun <T> DataLoader(
    resourceFlow: Flow<Resource<T>>,
    scaffoldState: ScaffoldState,
    navController: NavController?,
    displayCircularProgressIndicator: Boolean = true,
    loadingContent: @Composable () -> Unit = {},
    noDataContent: @Composable (T?) -> Unit,
    content: @Composable (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycleAware = remember(resourceFlow, lifecycleOwner) {
        resourceFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val state = flowLifecycleAware.collectAsState(initial = Resource.loading())

    ResourceLoaderImpl(
        state,
        scaffoldState,
        navController,
        displayCircularProgressIndicator,
        loadingContent,
        noDataContent,
        content
    )
}

@Composable
private fun <T> ResourceLoaderImpl(
    state: State<Resource<T>>,
    scaffoldState: ScaffoldState,
    navController: NavController? = null,
    displayCircularProgressIndicator: Boolean,
    loadingContent: @Composable () -> Unit = {},
    noDataContent: @Composable (T?) -> Unit,
    content: @Composable (T) -> Unit
) {
    val resource = state.value
    val isDataNullOrEmpty = resource.isDataNullOrEmptyList()

    DisplayContent(resource.data, isDataNullOrEmpty, content)
    DisplayNoDataContent(resource, isDataNullOrEmpty, noDataContent)

    when (resource.status) {
        Resource.Status.SUCCESS -> {
        }
        Resource.Status.ERROR -> {
            ProcessError(resource, scaffoldState, navController)
        }
        Resource.Status.LOADING -> {
            if (isDataNullOrEmpty) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // Loading animation
                    var delayed by remember { mutableStateOf(false) }

                    LaunchedEffect(key1 = true) {
                        delay(150L)
                        delayed = true
                    }

                    if (delayed) {
                        loadingContent()

                        if (displayCircularProgressIndicator) {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(KalorickeTabulkyLiteTheme.paddings.defaultPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> DisplayContent(
    data: T?,
    isDataNullOrEmpty: Boolean,
    content: @Composable (T) -> Unit
) {
    if (!isDataNullOrEmpty) {
        content(data!!)
    }
}

@Composable
private fun <T> DisplayNoDataContent(
    resource: Resource<T>,
    isDataNullOrEmpty: Boolean,
    noDataContent: @Composable (T?) -> Unit
) {
    if (resource.status != Resource.Status.LOADING && isDataNullOrEmpty) {
        noDataContent(resource.data)
    }
}

@Composable
private fun <T> ProcessError(
    resource: Resource<T>,
    scaffoldState: ScaffoldState,
    navController: NavController? = null,
) {
    when (resource.throwable) {
        is UnknownHostException -> {
            val message = stringResource(id = com.example.kaloricketabulkylite.R.string.no_internet)
            LaunchedEffect(true) {
                if (scaffoldState.snackbarHostState.currentSnackbarData?.message != message) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = null,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
        is EOFException, is UnsuccessfulCallException -> {
            val message = stringResource(com.example.kaloricketabulkylite.R.string.data_initialization_error)
            LaunchedEffect(true) {
                if (scaffoldState.snackbarHostState.currentSnackbarData?.message != message) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = null,
                        duration = SnackbarDuration.Short
                    )
                }
            }

            Timber.e(resource.throwable)
        }
        else -> {
            LaunchedEffect(true) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = resource.throwable.toString(),
                    actionLabel = "OK",
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }
}