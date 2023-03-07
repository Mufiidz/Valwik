package id.my.mufidz.valwik.data.local.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import id.my.mufidz.valwik.enums.ColorPaletteMode
import id.my.mufidz.valwik.enums.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

interface PreferencesRepository {
    suspend fun getUserPreference(): UserPreferencesData
    suspend fun fetchInitialPreferences(): UserPreferencesData
    suspend fun updatePreferences(userPreferencesData: UserPreferencesData)
    suspend fun updateName(name: String?)
    suspend fun updateColorPalette(colorPaletteMode: ColorPaletteMode)
    suspend fun updateThemeMode(themeMode: ThemeMode)
    suspend fun updateSystemFont(isSystemFont: Boolean)
}

class PreferencesRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    PreferencesRepository {

    private object UserPreferencesKeys {
        val NAME = stringPreferencesKey("name")
        val COLOR_PALETTE = stringPreferencesKey("color_palette")
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val IS_SYSTEM_FONT = booleanPreferencesKey("isSystemFont")
    }

    private val userPreferencesDataFlow: Flow<UserPreferencesData> = dataStore.data.catch {
        if (it is IOException) {
            Timber.e(it, "Error reading preferences.")
            emit(emptyPreferences())
        } else throw it
    }.map { mapUserPreferences(it) }

    override suspend fun getUserPreference(): UserPreferencesData = userPreferencesDataFlow.first()

    override suspend fun fetchInitialPreferences(): UserPreferencesData =
        mapUserPreferences(dataStore.data.first().toPreferences())

    override suspend fun updatePreferences(userPreferencesData: UserPreferencesData) {
        dataStore.edit {
            it[UserPreferencesKeys.NAME] = userPreferencesData.name
            it[UserPreferencesKeys.COLOR_PALETTE] = userPreferencesData.appearancePreferencesData.colorPalette
            it[UserPreferencesKeys.THEME_MODE] = userPreferencesData.appearancePreferencesData.themeMode
            it[UserPreferencesKeys.IS_SYSTEM_FONT] = userPreferencesData.isSystemFont
        }
    }

    override suspend fun updateName(name: String?) {
        dataStore.edit { it[UserPreferencesKeys.NAME] = name ?: "USER" }
    }

    override suspend fun updateColorPalette(colorPaletteMode: ColorPaletteMode) {
        dataStore.edit { it[UserPreferencesKeys.COLOR_PALETTE] = colorPaletteMode.name }
    }

    override suspend fun updateThemeMode(themeMode: ThemeMode) {
        dataStore.edit { it[UserPreferencesKeys.THEME_MODE] = themeMode.name }
    }

    override suspend fun updateSystemFont(isSystemFont: Boolean) {
        dataStore.edit { it[UserPreferencesKeys.IS_SYSTEM_FONT] = isSystemFont }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferencesData {
        val name = preferences[UserPreferencesKeys.NAME] ?: ""
        val colorPaletteMode =
            preferences[UserPreferencesKeys.COLOR_PALETTE] ?: ColorPaletteMode.Default.name
        val themeMode = preferences[UserPreferencesKeys.THEME_MODE] ?: ThemeMode.System.name
        val isSystemFont = preferences[UserPreferencesKeys.IS_SYSTEM_FONT] ?: false
        return UserPreferencesData(
            name,
            AppearancePreferencesData(colorPaletteMode, themeMode),
            isSystemFont
        )
    }
}
