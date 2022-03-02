package com.example.kaloricketabulkylite.ui.screens.detail.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.kaloricketabulkylite.R
import com.example.kaloricketabulkylite.data.local.entity.potravina.PotravinaEntity
import com.example.kaloricketabulkylite.ui.components.Chip
import com.example.kaloricketabulkylite.ui.components.ChipGroup
import com.example.kaloricketabulkylite.ui.components.SpacerDefault
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun Hodnoty(
    potravina: PotravinaEntity
) {
    potravina.hodnoty?.let { hodnoty ->
        SpacerDefault()

        Text(
            text = "Hodnoty",
            style = KalorickeTabulkyLiteTheme.typography.h6
        )

        val jednotkySorted =
            potravina.jednotky.sortedBy { it.nasobek ?: 0 }
        val jednotkySelected = remember {
            mutableStateOf(jednotkySorted.first())
        }

        ChipGroup(
            chips = jednotkySorted.map {
                Chip(
                    name = it.value ?: "",
                    isSelected = jednotkySelected.value == it
                )
            },
            onSelectedChanged = { newName ->
                jednotkySelected.value =
                    jednotkySorted.firstOrNull { it.value == newName }
                        ?: jednotkySorted.first()
            },
            modifier = Modifier.padding(vertical = KalorickeTabulkyLiteTheme.paddings.smallPadding)
        )

        HodnotaDetail(
            title = stringResource(R.string.energy),
            hodnota = hodnoty.energie,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.bilkoviny),
            hodnota = hodnoty.bilkoviny,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.sacharidy),
            hodnota = hodnoty.sacharidy,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.sodik),
            hodnota = hodnoty.sodik,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.vapnik),
            hodnota = hodnoty.vapnik,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.tuky),
            hodnota = hodnoty.tuky,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.vlaknina),
            hodnota = hodnoty.vlaknina,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.sul),
            hodnota = hodnoty.sul,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.cholesterol),
            hodnota = hodnoty.cholesterol,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.cukry),
            hodnota = hodnoty.cukry,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.mononenasycene),
            hodnota = hodnoty.mononenasycene,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.polynenasycene),
            hodnota = hodnoty.polynenasycene,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.nasyceneMastneKyseliny),
            hodnota = hodnoty.nasyceneMastneKyseliny,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.transmastneKyseliny),
            hodnota = hodnoty.transmastneKyseliny,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.voda),
            hodnota = hodnoty.voda,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = stringResource(R.string.alcohol),
            hodnota = hodnoty.alcohol,
            jednotka = jednotkySelected.value
        )
    }
}