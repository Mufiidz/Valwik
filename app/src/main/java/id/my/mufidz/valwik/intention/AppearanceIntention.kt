package id.my.mufidz.valwik.intention

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.ViewAction
import id.my.mufidz.valwik.base.ViewState
import id.my.mufidz.valwik.data.local.prefs.AppearancePreferencesData
import id.my.mufidz.valwik.data.local.prefs.UserPreferencesData
import kotlinx.parcelize.Parcelize

sealed class AppearanceAction : ViewAction {
    object LoadPreference : AppearanceAction()
    data class UpdatePreference(val userPreference: UserPreferencesData) : AppearanceAction()
}

sealed class AppearanceResult : ActionResult {
    data class UserPreference(val userPreference: UserPreferencesData) : AppearanceResult()
}

@Parcelize
data class AppearanceViewState(
    var isLoading: Boolean = true,
    var colorPalette: String = "",
    var themeMode: String = "",
    var isSystemFont : Boolean = false
) : ViewState {
    fun toUserPreference() = UserPreferencesData(
        appearancePreferencesData = AppearancePreferencesData(colorPalette, themeMode),
        isSystemFont = isSystemFont
    )
}