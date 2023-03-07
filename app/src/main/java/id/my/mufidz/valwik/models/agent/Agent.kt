package id.my.mufidz.valwik.models.agent

import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class Agent(
    val abilities: List<Ability> = listOf(),
    val assetPath: String = "",
    val background: String = "",
    val backgroundGradientColors: List<String> = listOf(),
    val bustPortrait: String = "",
    val characterTags: List<String> = listOf(),
    val description: String = "",
    val developerName: String = "",
    val displayIcon: String = "",
    val displayIconSmall: String = "",
    val displayName: String = "",
    val fullPortrait: String = "",
    val fullPortraitV2: String = "",
    val isAvailableForTest: Boolean = false,
    val isBaseContent: Boolean = false,
    val isFullPortraitRightFacing: Boolean = false,
    val isPlayableCharacter: Boolean = false,
    val killfeedPortrait: String = "",
    val role: Role = Role(),
    val uuid: String = "",
) : Parcelable