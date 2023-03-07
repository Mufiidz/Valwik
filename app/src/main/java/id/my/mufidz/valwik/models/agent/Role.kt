package id.my.mufidz.valwik.models.agent

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class Role(
    val assetPath: String = "",
    val description: String = "",
    val displayIcon: String = "",
    val displayName: String = "",
    val uuid: String = ""
) : Parcelable