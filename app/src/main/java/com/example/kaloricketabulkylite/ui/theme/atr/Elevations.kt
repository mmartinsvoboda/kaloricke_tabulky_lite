/*
 * Created by  Martin Svoboda
 * Copyright (c) IS4U s.r.o. 2021. All rights reserved.
 * Last modified 24.11.21 13:39
 */

package cz.is4uma.moje_studium.ui.theme.attr

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Elevation values that can be themed.
 */
@Immutable
data class Elevations(val card: Dp = 0.dp)

internal val LocalElevations = staticCompositionLocalOf { Elevations() }
