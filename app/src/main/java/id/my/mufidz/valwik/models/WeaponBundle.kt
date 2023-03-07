package id.my.mufidz.valwik.models

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Serializable
data class WeaponBundle(
    val assetPath: String = "",
    val description: String = "",
    val displayIcon: String = "",
    val displayIcon2: String = "",
    val displayName: String = "",
    val displayNameSubText: String = "",
    val extraDescription: String = "",
    val promoDescription: String = "",
    val useAdditionalContext: Boolean = false,
    val uuid: String = "",
    val verticalPromoImage: String = ""
) : Parcelable