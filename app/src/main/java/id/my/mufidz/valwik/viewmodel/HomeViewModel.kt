package id.my.mufidz.valwik.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.mufidz.valwik.base.BaseViewModel
import id.my.mufidz.valwik.intention.HomeAction
import id.my.mufidz.valwik.intention.HomeResult
import id.my.mufidz.valwik.intention.HomeViewState
import id.my.mufidz.valwik.usecase.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val agentsUseCase: GetAgentsUseCase,
    private val mapsUseCase: GetMapsUseCase,
    private val weaponUseCase: GetWeaponUseCase,
    private val weaponBundleUseCase: GetWeaponBundleUseCase,
    private val gameUpdateUseCase: GetGameUpdateUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeViewState, HomeAction, HomeResult>(HomeViewState(), savedStateHandle) {

    override fun HomeResult.updateViewState(): HomeViewState = when (this) {
        is HomeResult.AgentsResult -> useCaseResult.mapResult()
        is HomeResult.GameUpdatesResult -> useCaseResult.mapResult()
        is HomeResult.MapsResult -> useCaseResult.mapResult()
        is HomeResult.WeaponBundlesResult -> useCaseResult.mapResult()
        is HomeResult.WeaponsResult -> useCaseResult.mapResult()
    }

    override fun HomeAction.handleAction(): Flow<HomeResult> = channelFlow {
        when (this@handleAction) {
            HomeAction.LoadHomeData -> {
                agentsUseCase.getResult().also { send(HomeResult.AgentsResult(it)) }
                mapsUseCase.getResult().also { send(HomeResult.MapsResult(it)) }
                weaponUseCase.getResult().also { send(HomeResult.WeaponsResult(it)) }
                weaponBundleUseCase.getResult().also { send(HomeResult.WeaponBundlesResult(it)) }
                gameUpdateUseCase.getResult().also { send(HomeResult.GameUpdatesResult(it)) }
            }
        }
    }

    private fun GetAgentsUseCase.UseCaseResult.mapResult(): HomeViewState = when (this) {
        is GetAgentsUseCase.UseCaseResult.Failed -> updateState {
            it.copy(
                isLoading = false,
                errorMessage = message
            )
        }
        is GetAgentsUseCase.UseCaseResult.Success -> updateState {
            it.copy(
                isLoading = false,
                agents = agents
            )
        }
    }

    private fun GetMapsUseCase.UseCaseResult.mapResult(): HomeViewState = when (this) {
        is GetMapsUseCase.UseCaseResult.Failed -> updateState {
            it.copy(
                isLoading = false,
                errorMessage = message
            )
        }
        is GetMapsUseCase.UseCaseResult.Success -> updateState {
            it.copy(
                isLoading = false,
                maps = maps
            )
        }
    }

    private fun GetWeaponUseCase.UseCaseResult.mapResult(): HomeViewState = when (this) {
        is GetWeaponUseCase.UseCaseResult.Failed -> updateState {
            it.copy(
                isLoading = false,
                errorMessage = message
            )
        }
        is GetWeaponUseCase.UseCaseResult.Success -> updateState {
            it.copy(
                isLoading = false,
                weapons = weapons
            )
        }
    }

    private fun GetWeaponBundleUseCase.UseCaseResult.mapResult(): HomeViewState = when (this) {
        is GetWeaponBundleUseCase.UseCaseResult.Failed -> updateState {
            it.copy(
                isLoading = false,
                errorMessage = message
            )
        }
        is GetWeaponBundleUseCase.UseCaseResult.Success -> updateState {
            it.copy(
                isLoading = false,
                weaponBundles = weaponBundles
            )
        }
    }

    private fun GetGameUpdateUseCase.UseCaseResult.mapResult(): HomeViewState = when (this) {
        is GetGameUpdateUseCase.UseCaseResult.Failed -> updateState {
            it.copy(
                isLoading = false,
                errorMessage = message
            )
        }
        is GetGameUpdateUseCase.UseCaseResult.Success -> updateState {
            it.copy(
                isLoading = false,
                news = news
            )
        }
    }
}
