package com.example.kaloricketabulkylite.ui.screens.detail.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
            title = "Energie",
            hodnota = hodnoty.energie,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Bílkoviny",
            hodnota = hodnoty.bilkoviny,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Sacharidy",
            hodnota = hodnoty.sacharidy,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Sodík",
            hodnota = hodnoty.sodik,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Vápník",
            hodnota = hodnoty.vapnik,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Tuky",
            hodnota = hodnoty.tuky,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Vláknina",
            hodnota = hodnoty.vlaknina,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Sůl",
            hodnota = hodnoty.sul,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Cholesterol",
            hodnota = hodnoty.cholesterol,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Cukry",
            hodnota = hodnoty.cukry,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Mononasycené",
            hodnota = hodnoty.mononenasycene,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Polynenasycené",
            hodnota = hodnoty.polynenasycene,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Nasycené",
            hodnota = hodnoty.nasyceneMastneKyseliny,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Transmastné",
            hodnota = hodnoty.transmastneKyseliny,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Voda",
            hodnota = hodnoty.voda,
            jednotka = jednotkySelected.value
        )

        HodnotaDetail(
            title = "Alkohol",
            hodnota = hodnoty.alcohol,
            jednotka = jednotkySelected.value
        )
    }
}