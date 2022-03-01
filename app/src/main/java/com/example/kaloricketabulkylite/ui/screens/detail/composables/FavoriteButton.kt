package com.example.kaloricketabulkylite.ui.screens.detail.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.kaloricketabulkylite.data.local.entity.potravina.PotravinaEntity
import com.example.kaloricketabulkylite.ui.components.CardKalorickeTabulkyLite
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun FavoriteButton(
    potravina: PotravinaEntity
) {
    val potravinaOblibena = remember {
        mutableStateOf(potravina.oblibena)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ) {
        CardKalorickeTabulkyLite(
            shape = CircleShape,
            modifier = Modifier
                .requiredSize(60.dp)
                .offset(
                    x = -(LocalConfiguration.current.screenWidthDp / 4).dp,
                    y = (-30).dp
                ),
            elevation = 8.dp
        ) {}

        IconToggleButton(
            checked = potravina.oblibena,
            onCheckedChange = {
                potravinaOblibena.value = !potravinaOblibena.value
            },
            modifier = Modifier
                .requiredSize(60.dp)
                .offset(
                    x = (-(LocalConfiguration.current.screenWidthDp / 4) - 12).dp,
                    y = (-30).dp
                )
                .padding(start = KalorickeTabulkyLiteTheme.paddings.largePadding)
        ) {
            val heartColor = animateColorAsState(
                targetValue = if (potravinaOblibena.value) Color.Red else Color.Gray
            )

            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = heartColor.value
            )
        }
    }
}