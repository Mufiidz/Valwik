package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class Chroma(
    val assetPath: String = "",
    val displayIcon: String = "",
    val displayName: String = "",
    val fullRender: String = "",
    val streamedVideo: String = "",
    val swatch: String = "",
    val uuid: String = ""
) : Parcelable