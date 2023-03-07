package id.my.mufidz.valwik.ui.style

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import id.my.mufidz.valwik.enums.ColorPaletteMode
import id.my.mufidz.valwik.enums.ColorPaletteMode.Dynamic
import id.my.mufidz.valwik.enums.ColorPaletteMode.PureBlack
import id.my.mufidz.valwik.enums.ThemeMode
import id.my.mufidz.valwik.enums.ThemeMode.*

@Composable
fun ValwikTheme(
    themeMode: ThemeMode,
    colorPaletteMode: ColorPaletteMode,
    isFontSystem: Boolean,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        Light -> false
        Dark -> true
        System -> isSystemInDarkTheme()
    }
    val context = LocalContext.current
    val dynamicColorScheme = if (colorPaletteMode == PureBlack) {
        DarkColorsScheme.copy(background = Color.Black)
    } else {
        when {
            colorPaletteMode == Dynamic && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> DarkColorsScheme
            else -> LightColorsScheme
        }
    }

    val typography = if (isFontSystem) Typography else AppTypography
    StatusBarSetup(darkTheme)

    val style = Style(typography, dynamicColorScheme, Shapes(), Spacing(), Elevation())

    CompositionLocalProvider(
        LocalStyle provides style,
        LocalSpacing provides Spacing(),
        LocalElevation provides Elevation()
    ) {
        MaterialTheme(colorScheme = dynamicColorScheme, typography = typography, content = {
            ProvideTextStyle(value = MaterialTheme.typography.bodySmall, content)
        })
    }
}

@Composable
private fun StatusBarSetup(darkTheme: Boolean) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }

            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }
}
