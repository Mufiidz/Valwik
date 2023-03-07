package id.my.mufidz.valwik.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.mufidz.valwik.base.BaseViewModel
import id.my.mufidz.valwik.data.local.prefs.PreferencesRepository
import id.my.mufidz.valwik.intention.AppearanceAction
import id.my.mufidz.valwik.intention.AppearanceResult
import id.my.mufidz.valwik.intention.AppearanceViewState
import id.my.mufidz.valwik.ui.style.ThemeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AppearanceViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository, savedStateHandle: SavedStateHandle
) : BaseViewModel<AppearanceViewState, AppearanceAction, AppearanceResult>(
    AppearanceViewState(), savedStateHandle
) {

    override fun AppearanceResult.updateViewState(): AppearanceViewState = when (this) {
        is AppearanceResult.UserPreference -> {
            Timber.d("appearance ${userPreference.isSystemFont}")
            updateState {
                it.copy(
                    isLoading = false,
                    themeMode = userPreference.appearancePreferencesData.themeMode,
                    colorPalette = userPreference.appearancePreferencesData.colorPalette,
                    isSystemFont = userPreference.isSystemFont
                )
            }
        }
    }

    override fun AppearanceAction.handleAction(): Flow<AppearanceResult> = flow {
        when (this@handleAction) {
            AppearanceAction.LoadPreference -> {
                delay(250)
                setValuePreferences()
            }
            is AppearanceAction.UpdatePreference -> {
                delay(250)
                preferencesRepository.updatePreferences(this@handleAction.userPreference)
                setValuePreferences()
            }
        }
    }

    private suspend fun FlowCollector<AppearanceResult>.setValuePreferences() {
        val preference = preferencesRepository.getUserPreference()
        val preferenceData = preference.appearancePreferencesData
        ThemeState.colorPaletteMode = enumValueOf(preferenceData.colorPalette)
        ThemeState.isDarkTheme = enumValueOf(preferenceData.themeMode)
        ThemeState.isSystemFont = preference.isSystemFont
        Timber.d(preferenceData.toString())
        emit(AppearanceResult.UserPreference(preference))
    }
}