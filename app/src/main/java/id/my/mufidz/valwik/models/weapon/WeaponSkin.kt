package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class WeaponSkin(
    val assetPath: String = "",
    val chromas: List<Chroma> = listOf(),
    val contentTierUuid: String = "",
    val displayIcon: String = "",
    val displayName: String = "",
    val levels: List<Level> = listOf(),
    val themeUuid: String = "",
    val uuid: String = "",
    val wallpaper: String = ""
) : Parcelable