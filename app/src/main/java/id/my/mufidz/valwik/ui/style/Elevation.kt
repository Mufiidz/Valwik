package id.my.mufidz.valwik.ui.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevation(
    val noElevation: Dp = 0.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 10.dp,
    val large: Dp = 12.dp
)

val LocalElevation = compositionLocalOf { Elevation() }

val MaterialTheme.elevation : Elevation
    @Composable
    @ReadOnlyComposable
    get() = LocalElevation.current