package id.my.mufidz.valwik.models

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class Maps(
    val assetPath: String = "",
    val coordinates: String = "",
    val displayIcon: String = "",
    val displayName: String = "",
    val listViewIcon: String = "",
    val mapUrl: String = "",
    val splash: String = "",
    val uuid: String = ""
) : Parcelable