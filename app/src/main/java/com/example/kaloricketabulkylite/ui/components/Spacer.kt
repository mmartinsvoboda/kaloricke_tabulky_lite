package com.example.kaloricketabulkylite.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@Composable
fun SpacerTiny() {
    Spacer(modifier = Modifier.size(KalorickeTabulkyLiteTheme.paddings.tinyPadding))
}

@Composable
fun SpacerSmall() {
    Spacer(modifier = Modifier.size(KalorickeTabulkyLiteTheme.paddings.smallPadding))
}

@Composable
fun SpacerDefault() {
    Spacer(modifier = Modifier.size(KalorickeTabulkyLiteTheme.paddings.defaultPadding))
}

@Composable
fun SpacerLarge() {
    Spacer(modifier = Modifier.size(KalorickeTabulkyLiteTheme.paddings.largePadding))
}