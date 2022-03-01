package com.example.kaloricketabulkylite.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.kaloricketabulkylite.R
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme
import com.example.kaloricketabulkylite.utils.network.ConnectionState
import com.example.kaloricketabulkylite.utils.network.NetworkUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalAnimationApi::class)
@Composable
fun ConnectivityStatus() {
    val connection by NetworkUtils().connectivityState()
    val isConnected = connection === ConnectionState.Available

    var visibility by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(isConnected = isConnected)
    }

    LaunchedEffect(isConnected) {
        visibility = if (!isConnected) {
            true
        } else {
            delay(2000)
            false
        }
    }
}

@Composable
fun ConnectivityStatusBox(isConnected: Boolean) {
    val backgroundColor by animateColorAsState(
        if (isConnected) Color(
            52,
            199,
            89
        ) else KalorickeTabulkyLiteTheme.colors.error
    )
    val message =
        if (isConnected) stringResource(id = R.string.back_online) else stringResource(id = R.string.no_internet_connection)
    val iconResource = if (isConnected) {
        R.drawable.ic_connectivity_available
    } else {
        R.drawable.ic_connectivity_unavailable
    }

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(bottom = KalorickeTabulkyLiteTheme.paddings.smallPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painterResource(id = iconResource),
                null,
                tint = KalorickeTabulkyLiteTheme.colors.onPrimary
            )
            SpacerSmall()
            Text(message, color = Color.White, fontSize = 15.sp)
        }
    }
}