package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class Level(
    val assetPath: String = "",
    val displayIcon: String = "",
    val displayName: String = "",
    val levelItem: String = "",
    val streamedVideo: String = "",
    val uuid: String = ""
) : Parcelable