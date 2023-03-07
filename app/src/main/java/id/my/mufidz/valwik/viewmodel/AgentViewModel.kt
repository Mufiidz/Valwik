package id.my.mufidz.valwik.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.mufidz.valwik.base.BaseViewModel
import id.my.mufidz.valwik.intention.*
import id.my.mufidz.valwik.usecase.GetAgentsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(
    private val agentsUseCase: GetAgentsUseCase,
    val savedStateHandle: SavedStateHandle
) : BaseViewModel<AgentViewState, AgentAction, AgentResult>(AgentViewState(), savedStateHandle) {

    override fun AgentResult.updateViewState(): AgentViewState = when (this) {
        is AgentResult.AgentsResult -> useCaseResult.mapResult()
    }

    override fun AgentAction.handleAction(): Flow<AgentResult> = channelFlow {
        when (this@handleAction) {
            AgentAction.LoadAgentData -> {
                delay(500)
                agentsUseCase.getResult().also { send(AgentResult.AgentsResult(it)) }
            }
        }
    }

    private fun GetAgentsUseCase.UseCaseResult.mapResult(): AgentViewState = when (this) {
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
}