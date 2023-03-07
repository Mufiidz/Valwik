package id.my.mufidz.valwik.ui.style

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import id.my.mufidz.valwik.enums.ColorPaletteMode
import id.my.mufidz.valwik.enums.ThemeMode

object ThemeState {
    var isDarkTheme by mutableStateOf(ThemeMode.System)
    var colorPaletteMode by mutableStateOf(ColorPaletteMode.Default)
    var isSystemFont by mutableStateOf(false)
}