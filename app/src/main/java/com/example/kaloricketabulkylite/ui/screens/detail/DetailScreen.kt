package com.example.kaloricketabulkylite.ui.screens.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kaloricketabulkylite.R
import com.example.kaloricketabulkylite.data.local.entity.potravina.PotravinaEntity
import com.example.kaloricketabulkylite.ui.components.*
import com.example.kaloricketabulkylite.ui.screens.detail.composables.FavoriteButton
import com.example.kaloricketabulkylite.ui.screens.detail.composables.Hodnoty
import com.example.kaloricketabulkylite.ui.screens.detail.composables.StitkyExpandableCard
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun DetailScreen(
    guidPotravina: String,
    navController: NavController,
    model: DetailViewModel = hiltViewModel()
) {
    val potravinaEntity = remember {
        mutableStateOf<PotravinaEntity?>(null)
    }

    Box(
        contentAlignment = Alignment.TopStart
    ) {
        ScaffoldKalorickeTabulkyLite(
            topBarTitle = "",
            displayTopAppBar = false,
            topBarDisplayNavigationIcon = false,
            navController = navController,
            modifier = Modifier
        ) { scaffoldState ->
            DataLoader(
                resourceFlow = model.potravinaRepository.getPotravina(guidPotravina),
                scaffoldState = scaffoldState,
                navController = navController,
                noDataContent = {
                    Text(
                        text = stringResource(R.string.item_not_found),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            ) { potravina ->
                potravinaEntity.value = potravina

                if (potravina == null) {
                    Text(
                        text = stringResource(R.string.item_not_found),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                } else {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.TopCenter
                    ) {
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
                                    .fillMaxWidth()
                                    .requiredHeight(300.dp),
                                contentScale = ContentScale.Crop
                            )
                        }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(KalorickeTabulkyLiteTheme.paddings.defaultPadding)
                        ) {
                            if (!potravina.foto.isNullOrEmpty()) {
                                item {
                                    Spacer(modifier = Modifier.height(250.dp))
                                }
                            }

                            item {
                                Box(
                                    contentAlignment = Alignment.TopCenter
                                ) {
                                    CardKalorickeTabulkyLite(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        shape = if (!potravina.foto.isNullOrEmpty())
                                            RoundedCornerShape(
                                                topStart = 20.dp,
                                                topEnd = 20.dp,
                                                bottomStart = 0.dp,
                                                bottomEnd = 0.dp
                                            )
                                        else RectangleShape,
                                        backgroundColor = KalorickeTabulkyLiteTheme.colors.background
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(KalorickeTabulkyLiteTheme.paddings.defaultPadding)
                                                .fillMaxSize()
                                        ) {
                                            SpacerDefault()

                                            if (potravina.foto.isNullOrEmpty()) {
                                                SpacerSmall()
                                            }

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = potravina.nazev.replaceFirstChar { it.uppercase() },
                                                    modifier = Modifier.weight(1f),
                                                    style = KalorickeTabulkyLiteTheme.typography.h4,
                                                    fontWeight = FontWeight.Bold
                                                )

                                                if (potravina.foto.isNullOrEmpty()) {
                                                    val potravinaOblibena = remember {
                                                        mutableStateOf(potravina.oblibena)
                                                    }

                                                    IconToggleButton(
                                                        checked = potravina.oblibena,
                                                        onCheckedChange = {
                                                            potravinaOblibena.value =
                                                                !potravinaOblibena.value
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

                                            if (potravina.kategorie.isNotBlank()) {
                                                SpacerSmall()
                                                Text(
                                                    text = potravina.kategorie.uppercase(),
                                                    modifier = Modifier.fillMaxWidth(),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    style = KalorickeTabulkyLiteTheme.typography.caption
                                                )
                                            }

                                            if (potravina.znacka.isNotBlank()) {
                                                SpacerTiny()
                                                Text(
                                                    text = potravina.znacka.uppercase(),
                                                    modifier = Modifier.fillMaxWidth(),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    style = KalorickeTabulkyLiteTheme.typography.caption
                                                )
                                            }

                                            if (potravina.stav.isNotBlank()) {
                                                SpacerTiny()
                                                Text(
                                                    text = potravina.stav.uppercase(),
                                                    modifier = Modifier.fillMaxWidth(),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    style = KalorickeTabulkyLiteTheme.typography.caption
                                                )
                                            }

                                            if (!potravina.popisObsah.isNullOrBlank()) {
                                                SpacerDefault()
                                                Text(
                                                    text = potravina.popisObsah,
                                                    modifier = Modifier.fillMaxWidth(),
                                                    style = KalorickeTabulkyLiteTheme.typography.body1
                                                )
                                            }

                                            if (!potravina.popisPrakticke.isNullOrBlank()) {
                                                SpacerDefault()
                                                Text(
                                                    text = stringResource(R.string.info_practical),
                                                    style = KalorickeTabulkyLiteTheme.typography.h6
                                                )
                                                SpacerTiny()
                                                Text(
                                                    text = potravina.popisPrakticke,
                                                    modifier = Modifier.fillMaxWidth(),
                                                    style = KalorickeTabulkyLiteTheme.typography.body1
                                                )
                                            }

                                            if (!potravina.popisZdravi.isNullOrBlank()) {
                                                SpacerDefault()
                                                Text(
                                                    text = stringResource(R.string.info_health),
                                                    style = KalorickeTabulkyLiteTheme.typography.h6
                                                )
                                                SpacerTiny()
                                                Text(
                                                    text = potravina.popisZdravi,
                                                    modifier = Modifier.fillMaxWidth(),
                                                    style = KalorickeTabulkyLiteTheme.typography.body1
                                                )
                                            }

                                            // hodnoty
                                            Hodnoty(potravina)

                                            StitkyExpandableCard(
                                                title = stringResource(R.string.ecka),
                                                stitkyList = potravina.stitkyEcka
                                            )

                                            StitkyExpandableCard(
                                                title = stringResource(R.string.minerals),
                                                stitkyList = potravina.stitkyMineraly
                                            )

                                            StitkyExpandableCard(
                                                title = stringResource(R.string.vitamins),
                                                stitkyList = potravina.stitkyVitaminy
                                            )

                                            StitkyExpandableCard(
                                                title = stringResource(R.string.health),
                                                stitkyList = potravina.stitkyZdravi
                                            )
                                        }
                                    }

                                    if (!potravina.foto.isNullOrEmpty()) {
                                        FavoriteButton(potravina = potravina)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!potravinaEntity.value?.foto.isNullOrEmpty()) {
            val surface = KalorickeTabulkyLiteTheme.colors.surface
            Box(
                Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(
                                surface.copy(alpha = 0.8f),
                                Color.Transparent
                            ),
                            startY = 0F,
                            endY = size.height * 0.15f
                        )

                        onDrawWithContent {
                            drawRect(brush = gradient)
                        }
                    }
            )
        }

        NavigationIconTopAppBarKalorickeTabulkyLite(navController)
    }
}