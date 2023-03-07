package id.my.mufidz.valwik.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import id.my.mufidz.valwik.enums.ColorPaletteMode
import id.my.mufidz.valwik.enums.ThemeMode
import id.my.mufidz.valwik.intention.AppearanceAction
import id.my.mufidz.valwik.intention.AppearanceViewState
import id.my.mufidz.valwik.ui.components.LoadingScreen
import id.my.mufidz.valwik.ui.screen.settings.components.ListPreference
import id.my.mufidz.valwik.ui.screen.settings.components.PreferenceCategory
import id.my.mufidz.valwik.ui.screen.settings.components.SwitchPreference
import id.my.mufidz.valwik.utils.enumToListString
import id.my.mufidz.valwik.viewmodel.AppearanceViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun AppearanceSettingScreen(appearanceViewModel: AppearanceViewModel = hiltViewModel()) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    appearanceViewModel.apply {
        execute(AppearanceAction.LoadPreference)

        val state by produceState(initialValue = viewState.value, key1 = lifecycle, key2 = this) {
            lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewState.collectLatest { value = it }
            }
        }

        Timber.d(state.toString())

        LoadingScreen(state.isLoading) {
            AppearanceSettingContent(state) {
                execute(AppearanceAction.UpdatePreference(it.toUserPreference()))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AppearanceSettingContent(
    appearancePreferencesData: AppearanceViewState = AppearanceViewState(),
    onAppearanceChange: (AppearanceViewState) -> Unit = {}
) {
    val listPalette = enumToListString<ColorPaletteMode>()
    val listTheme = enumToListString<ThemeMode>()
    Timber.d("init state -> $appearancePreferencesData")

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
        PreferenceCategory(title = "Colors") {
            ListPreference(
                title = "Color Mode", listPalette, appearancePreferencesData.colorPalette
            ) {
                appearancePreferencesData.colorPalette = it
                onAppearanceChange(appearancePreferencesData)
            }
            ListPreference(title = "Theme Mode", listTheme, appearancePreferencesData.themeMode) {
                appearancePreferencesData.themeMode = it
                onAppearanceChange(appearancePreferencesData)
            }
        }
        Timber.d("font -> ${appearancePreferencesData.isSystemFont}")
        PreferenceCategory(title = "Text") {
            SwitchPreference(
                "Use system font",
                "Use the font applied by the system",
                appearancePreferencesData.isSystemFont
            ) {
                appearancePreferencesData.isSystemFont = it
                onAppearanceChange(appearancePreferencesData)
            }
        }
    }
}