package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class DamageRange(
    val bodyDamage: Int = 0,
    val headDamage: Double = 0.0,
    val legDamage: Double = 0.0,
    val rangeEndMeters: Int = 0,
    val rangeStartMeters: Int = 0
) : Parcelable