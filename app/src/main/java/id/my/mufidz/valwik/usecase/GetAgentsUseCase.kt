package id.my.mufidz.valwik.usecase

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.BaseUseCase
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.models.ValorantApiResponse
import id.my.mufidz.valwik.models.agent.Agent
import id.my.mufidz.valwik.utils.DataResult
import javax.inject.Inject

class GetAgentsUseCase @Inject constructor(private val apiServices: ApiServices) :
    BaseUseCase<Nothing, ValorantApiResponse<List<Agent>>, GetAgentsUseCase.UseCaseResult>() {

    override suspend fun execute(param: Nothing?): DataResult<ValorantApiResponse<List<Agent>>> =
        apiServices.getAgents()

    override suspend fun DataResult<ValorantApiResponse<List<Agent>>>.transformToResult(): UseCaseResult =
        when (this) {
            is DataResult.Failure -> UseCaseResult.Failed(exception.localizedMessage.orEmpty())
            is DataResult.Success -> {
                val data = value.data ?: emptyList()
                UseCaseResult.Success(data.map { agent ->
                    // Found some trouble when Navigation so, this abilities set to empty list
                    agent.copy(abilities = emptyList())
                }.sortedBy { it.displayName })
            }
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val agents: List<Agent>) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}