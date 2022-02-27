/*
 * Created by  Martin Svoboda
 * Copyright (c) IS4U s.r.o. 2021. All rights reserved.
 * Last modified 24.11.21 13:39
 */

package com.example.kaloricketabulkylite.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun Chip(
    chip: Chip,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(horizontal = KalorickeTabulkyLiteTheme.paddings.tinyPadding),
        shape = RoundedCornerShape(12.dp),
        color = if (chip.isSelected) chip.backgroundColor
            ?: KalorickeTabulkyLiteTheme.colors.primary else Color.LightGray
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = chip.isSelected,
                    onValueChange = {
                        onSelectionChanged(chip.name)
                    },
                    enabled = chip.enabled
                )
        ) {
            Text(
                text = chip.name,
                style = KalorickeTabulkyLiteTheme.typography.body2,
                color = if (chip.isSelected) chip.textColor
                    ?: KalorickeTabulkyLiteTheme.colors.onPrimary else Color.Black,
                modifier = Modifier.padding(
                    horizontal = KalorickeTabulkyLiteTheme.paddings.smallPadding,
                    vertical = KalorickeTabulkyLiteTheme.paddings.tinyPadding
                )
            )
        }
    }
}

@Composable
fun ChipGroup(
    chips: List<Chip?>,
    modifier: Modifier = Modifier,
    onSelectedChanged: (String) -> Unit = {},
) {
    Column(modifier = modifier) {
        LazyRow {
            items(chips.filterNotNull()) { chip ->
                Chip(
                    chip = chip,
                    onSelectionChanged = {
                        onSelectedChanged(chip.name)
                    },
                )
            }
        }
    }
}

data class Chip(
    val name: String,
    val isSelected: Boolean,
    val enabled: Boolean = true,
    val backgroundColor: Color? = null,
    val textColor: Color? = null
)