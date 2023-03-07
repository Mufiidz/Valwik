package id.my.mufidz.valwik.data.local.prefs

import id.my.mufidz.valwik.enums.ThemeMode

data class UserPreferencesData(
    val name: String = "USERS",
    val appearancePreferencesData: AppearancePreferencesData,
    val isSystemFont: Boolean
)

data class AppearancePreferencesData(
    val colorPalette: String = "",
    val themeMode: String = ThemeMode.System.name
)