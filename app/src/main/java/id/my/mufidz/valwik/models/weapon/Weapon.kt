package id.my.mufidz.valwik.models.weapon

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Weapon(
    val assetPath: String = "",
    val category: String = "",
    val defaultSkinUuid: String = "",
    val displayIcon: String = "",
    val displayName: String = "",
    val killStreamIcon: String = "",
    val shopData: ShopData = ShopData(),
    val uuid: String = "",
    val weaponStats: WeaponStats = WeaponStats()
) : Parcelable