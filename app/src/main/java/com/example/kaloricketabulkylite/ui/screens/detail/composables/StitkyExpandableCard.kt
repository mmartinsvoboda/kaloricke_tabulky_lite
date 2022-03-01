package com.example.kaloricketabulkylite.ui.screens.detail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kaloricketabulkylite.data.local.entity.potravina.Stitek
import com.example.kaloricketabulkylite.ui.components.CardKalorickeTabulkyLite
import com.example.kaloricketabulkylite.ui.components.ExpandableCard
import com.example.kaloricketabulkylite.ui.components.SpacerDefault
import com.example.kaloricketabulkylite.ui.components.SpacerSmall
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun StitkyExpandableCard(
    title: String,
    stitkyList: List<Stitek>?
) {
    if (stitkyList?.isNotEmpty() == true) {
        SpacerDefault()
        Text(
            text = title,
            style = KalorickeTabulkyLiteTheme.typography.h6
        )
        SpacerSmall()

        CardKalorickeTabulkyLite() {
            Column() {
                stitkyList.forEach {
                    ExpandableCard(
                        title = it.nazev ?: ""
                    ) {
                        Text(
                            text = it.value ?: "",
                            style = KalorickeTabulkyLiteTheme.typography.body1,
                            modifier = Modifier.padding(
                                KalorickeTabulkyLiteTheme.paddings.defaultPadding
                            )
                        )
                    }
                }
            }
        }
    }
}