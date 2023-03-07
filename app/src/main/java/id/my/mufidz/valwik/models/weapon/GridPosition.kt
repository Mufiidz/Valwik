package id.my.mufidz.valwik.models.weapon

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class GridPosition(
    val column: Int = 0,
    val row: Int = 0
) : Parcelable