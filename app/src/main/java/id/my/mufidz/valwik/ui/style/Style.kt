package id.my.mufidz.valwik.ui.style

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

data class Style(
    val typo: Typography,
    val color: ColorScheme,
    val shape: Shapes,
    val spacing: Spacing,
    val elevation: Elevation
)

val LocalStyle =
    staticCompositionLocalOf<Style> { error("CompositionLocal LocalStyle not present") }

val MaterialTheme.style: Style
    @Composable
    @ReadOnlyComposable
    get() = LocalStyle.current