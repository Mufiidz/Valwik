package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class AdsStats(
    val burstCount: Int = 0,
    val fireRate: Double = 0.0,
    val firstBulletAccuracy: Double = 0.0,
    val runSpeedMultiplier: Double = 0.0,
    val zoomMultiplier: Double = 0.0
) : Parcelable