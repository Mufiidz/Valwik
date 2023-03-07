package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class WeaponStats(
    val adsStats: AdsStats = AdsStats(),
    val damageRanges: List<DamageRange> = listOf(),
    val equipTimeSeconds: Double = 0.0,
    val fireMode: String = "",
    val fireRate: Double = 0.0,
    val firstBulletAccuracy: Double = 0.0,
    val magazineSize: Int = 0,
    val reloadTimeSeconds: Double = 0.0,
    val runSpeedMultiplier: Double = 0.0,
    val shotgunPelletCount: Int = 0,
) : Parcelable
