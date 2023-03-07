package id.my.mufidz.valwik.intention

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.ViewAction
import id.my.mufidz.valwik.base.ViewState
import id.my.mufidz.valwik.models.agent.Agent
import id.my.mufidz.valwik.usecase.*
import kotlinx.parcelize.Parcelize

sealed class AgentAction : ViewAction {
    object LoadAgentData : AgentAction()
}

sealed class AgentResult : ActionResult {
    data class AgentsResult(val useCaseResult: GetAgentsUseCase.UseCaseResult) : AgentResult()
}

@Parcelize
data class AgentViewState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val agents: List<Agent> = emptyList(),
) : ViewState