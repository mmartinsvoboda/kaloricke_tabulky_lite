package com.example.kaloricketabulkylite.ui.screens.search.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kaloricketabulkylite.data.local.entity.search.SearchEntity
import com.example.kaloricketabulkylite.ui.components.CardKalorickeTabulkyLite
import com.example.kaloricketabulkylite.ui.components.SpacerTiny
import com.example.kaloricketabulkylite.ui.navigation.Screen
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme
import kotlin.math.roundToLong

@Composable
fun PotravinaCard(
    modifier: Modifier,
    potravina: SearchEntity,
    navController: NavController,
    jednotka: String
) {
    CardKalorickeTabulkyLite(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate(Screen.Detail.withArgs(potravina.guidPotravina))
            }
            .fillMaxWidth()
            .heightIn(min = 95.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            // image
            if (!potravina.foto.isNullOrEmpty()) {
                val painter = rememberImagePainter(
                    data = potravina.foto,
                    builder = {
                        crossfade(true)
                    }
                )

                Image(
                    painter,
                    contentDescription = potravina.nazev,
                    modifier = Modifier
                        .requiredWidth(95.dp)
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            }

            // content
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            horizontal = KalorickeTabulkyLiteTheme.paddings.defaultPadding,
                            vertical = KalorickeTabulkyLiteTheme.paddings.smallPadding
                        )
                        .height(IntrinsicSize.Max)
                        .weight(1f)
                ) {
                    Text(
                        text = potravina.nazev.replaceFirstChar { it.uppercase() },
                        modifier = Modifier,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = KalorickeTabulkyLiteTheme.typography.h6
                    )

                    SpacerTiny()

                    Text(text = "${potravina.energie.value?.roundToLong()} $jednotka/${potravina.energie.jedn}")
                }

                val potravinaOblibena = remember {
                    mutableStateOf(potravina.oblibena)
                }

                IconToggleButton(
                    checked = potravina.oblibena,
                    onCheckedChange = {
                        potravinaOblibena.value = !potravinaOblibena.value
                    },
                    modifier = Modifier.padding(end = KalorickeTabulkyLiteTheme.paddings.smallPadding)
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
    }
}