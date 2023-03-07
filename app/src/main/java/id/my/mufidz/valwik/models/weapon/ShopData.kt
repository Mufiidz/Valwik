package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class ShopData(
    val assetPath: String = "",
    val canBeTrashed: Boolean = false,
    val category: String = "",
    val categoryText: String = "",
    val cost: Int = 0,
    val gridPosition: GridPosition = GridPosition(),
    val image: String = "",
    val newImage: String = "",
    val newImage2: String = ""
) : Parcelable