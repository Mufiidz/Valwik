package id.my.mufidz.valwik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import id.my.mufidz.valwik.data.local.prefs.PreferencesRepository
import id.my.mufidz.valwik.enums.ColorPaletteMode
import id.my.mufidz.valwik.enums.ThemeMode
import id.my.mufidz.valwik.ui.screen.NavGraphs
import id.my.mufidz.valwik.ui.style.ThemeState
import id.my.mufidz.valwik.ui.style.ValwikTheme
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@OptIn(ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesRepository: PreferencesRepository

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val userPreferenceData = runBlocking { preferencesRepository.fetchInitialPreferences() }
        val themeMode =
            safeEnum(userPreferenceData.appearancePreferencesData.themeMode, ThemeMode.System)
        val colorPaletteMode =
            safeEnum(
                userPreferenceData.appearancePreferencesData.colorPalette,
                ColorPaletteMode.Default
            )
        val isFontSystem = userPreferenceData.isSystemFont
        ThemeState.isDarkTheme = themeMode
        ThemeState.colorPaletteMode = colorPaletteMode
        ThemeState.isSystemFont = isFontSystem

        setContent {
            ValwikTheme(
                ThemeState.isDarkTheme,
                ThemeState.colorPaletteMode,
                ThemeState.isSystemFont
            ) {

                val navHostEngine = rememberAnimatedNavHostEngine(
                    rootDefaultAnimations = RootNavGraphDefaultAnimations(
                        enterTransition = { slideInHorizontally { 1000 } },
                        exitTransition = { slideOutHorizontally { -1000 } },
                        popEnterTransition = { slideInHorizontally { -1000 } },
                        popExitTransition = { slideOutHorizontally { 1000 } }
                    ),
                )

                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    engine = navHostEngine
                )
            }
        }
    }
}

private inline fun <reified T : Enum<T>> safeEnum(value: String, defaultValue: T): T =
    try {
        enumValueOf(value)
    } catch (e: Exception) {
        defaultValue
    }