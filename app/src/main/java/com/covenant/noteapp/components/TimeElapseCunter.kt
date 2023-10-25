package com.covenant.noteapp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.covenant.noteapp.R
import java.time.Duration
import java.time.Instant

@Composable
fun Instant?.timeElapsedString(): String {
    if (this == null) {
        return stringResource(R.string.untouched)
    }

    val currentTime = Instant.now()
    val duration = Duration.between(this, currentTime)

    return when {
        currentTime < this -> stringResource(R.string.lost_in_time)
        duration.toMinutes() < 1 -> stringResource(R.string.just_now)
        duration.toHours() < 1 -> pluralStringResource(
            R.plurals.minutes,
            duration.toMinutes().toInt(),
            duration.toMinutes()
        )
        duration.toDays() < 1 -> pluralStringResource(
            R.plurals.hours,
            duration.toHours().toInt(),
            duration.toHours()
        )
        else -> pluralStringResource(
            R.plurals.days,
            duration.toDays().toInt(),
            duration.toDays()
        )
    }
}