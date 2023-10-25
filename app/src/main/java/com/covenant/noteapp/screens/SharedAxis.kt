package com.covenant.noteapp.screens

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

fun Density.sharedXAxisEnter(forward: Boolean = true) =
    slideInHorizontally(
        animationSpec = tween(durationMillis = 300)
    ) {
        val directionMultiplier = if (forward) 1 else -1
        with(this) { (30 * directionMultiplier).dp.toPx().toInt() }
    } + fadeIn(
        animationSpec = tween(
            delayMillis = 90,
            durationMillis = 210,
        ),
    )

fun Density.sharedXAxisExit(forward: Boolean = true) =
    slideOutHorizontally(
        animationSpec = tween(durationMillis = 300)
    ) {
        val directionMultiplier = if (forward) -1 else 1
        with(this) { (30 * directionMultiplier).dp.toPx().toInt() }
    } + fadeOut(animationSpec = tween(durationMillis = 90))