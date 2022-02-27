/*
 * Created by  Martin Svoboda
 * Copyright (c) IS4U s.r.o. 2021. All rights reserved.
 * Last modified 10.02.22 13:11
 */

package com.example.kaloricketabulkylite.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardKalorickeTabulkyLite(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    backgroundColor: Color = KalorickeTabulkyLiteTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        content = content
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String? = null,
    descriptionFontSize: TextUnit = MaterialTheme.typography.subtitle2.fontSize,
    descriptionFontWeight: FontWeight = FontWeight.Light,
    shape: Shape = RoundedCornerShape(12.dp),
    defaultExpandedState: Boolean = false,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    var expandedState by remember { mutableStateOf(defaultExpandedState) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    val colorState by animateColorAsState(
        targetValue = if (expandedState) KalorickeTabulkyLiteTheme.colors.primary else KalorickeTabulkyLiteTheme.colors.surface
    )

    val onColorState by animateColorAsState(
        targetValue = if (expandedState) KalorickeTabulkyLiteTheme.colors.onPrimary else KalorickeTabulkyLiteTheme.colors.onSurface
    )

    CardKalorickeTabulkyLite(
        modifier = modifier
            .clickable { expandedState = !expandedState }
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .background(color = colorState)
                    .padding(
                        start = KalorickeTabulkyLiteTheme.paddings.defaultPadding,
                        top = KalorickeTabulkyLiteTheme.paddings.tinyPadding,
                        end = KalorickeTabulkyLiteTheme.paddings.tinyPadding,
                        bottom = KalorickeTabulkyLiteTheme.paddings.tinyPadding
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier.weight(1f)
                ) {
                    Text(
                        modifier = Modifier,
                        text = title,
                        fontSize = titleFontSize,
                        fontWeight = titleFontWeight,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = onColorState
                    )

                    if (!description.isNullOrBlank()) {
                        Text(
                            modifier = Modifier,
                            text = description,
                            fontSize = descriptionFontSize,
                            fontWeight = descriptionFontWeight,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = onColorState
                        )
                    }
                }

                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    },
                    enabled = enabled
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow",
                        tint = onColorState
                    )
                }
            }

            if (expandedState) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: @Composable (expandedState: Boolean) -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    defaultExpandedState: Boolean = false,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    var expandedState by remember { mutableStateOf(defaultExpandedState) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    val colorState by animateColorAsState(
        targetValue = if (expandedState) KalorickeTabulkyLiteTheme.colors.primary else KalorickeTabulkyLiteTheme.colors.surface
    )

    val onColorState by animateColorAsState(
        targetValue = if (expandedState) KalorickeTabulkyLiteTheme.colors.onPrimary else KalorickeTabulkyLiteTheme.colors.onSurface
    )

    CardKalorickeTabulkyLite(
        modifier = modifier
            .clickable {
                expandedState = !expandedState
            }
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .background(color = colorState)
                    .padding(
                        start = KalorickeTabulkyLiteTheme.paddings.defaultPadding,
                        top = KalorickeTabulkyLiteTheme.paddings.tinyPadding,
                        end = KalorickeTabulkyLiteTheme.paddings.tinyPadding,
                        bottom = KalorickeTabulkyLiteTheme.paddings.tinyPadding
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier.weight(1f)
                ) {
                    title(expandedState)
                }

                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    },
                    enabled = enabled
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow",
                        tint = onColorState
                    )
                }
            }

            if (expandedState) {
                content()
            }
        }
    }
}