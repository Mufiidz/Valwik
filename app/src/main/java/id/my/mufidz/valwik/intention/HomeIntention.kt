package id.my.mufidz.valwik.intention

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.ViewAction
import id.my.mufidz.valwik.base.ViewState
import id.my.mufidz.valwik.models.*
import id.my.mufidz.valwik.models.agent.Agent
import id.my.mufidz.valwik.models.weapon.Weapon
import id.my.mufidz.valwik.usecase.*
import kotlinx.parcelize.Parcelize

sealed class HomeAction : ViewAction {
    object LoadHomeData : HomeAction()
}

sealed class HomeResult : ActionResult {
    data class AgentsResult(val useCaseResult: GetAgentsUseCase.UseCaseResult) : HomeResult()
    data class MapsResult(val useCaseResult: GetMapsUseCase.UseCaseResult) : HomeResult()
    data class WeaponsResult(val useCaseResult: GetWeaponUseCase.UseCaseResult) : HomeResult()
    data class WeaponBundlesResult(val useCaseResult: GetWeaponBundleUseCase.UseCaseResult) :
        HomeResult()

    data class GameUpdatesResult(val useCaseResult: GetGameUpdateUseCase.UseCaseResult) :
        HomeResult()
}

@Parcelize
data class HomeViewState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val agents: List<Agent> = emptyList(),
    val maps: List<Maps> = emptyList(),
    val weapons: List<Weapon> = emptyList(),
    val weaponBundles: List<WeaponBundle> = emptyList(),
    val news: List<News> = emptyList()
) : ViewState