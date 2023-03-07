package id.my.mufidz.valwik.usecase

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.BaseUseCase
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.models.News
import id.my.mufidz.valwik.models.ValorantApiResponse
import id.my.mufidz.valwik.utils.DataResult
import javax.inject.Inject

class GetGameUpdateUseCase @Inject constructor(private val apiServices: ApiServices) :
    BaseUseCase<Nothing, ValorantApiResponse<List<News>>, GetGameUpdateUseCase.UseCaseResult>() {
    override suspend fun execute(param: Nothing?): DataResult<ValorantApiResponse<List<News>>> =
        apiServices.getGameUpdates()

    override suspend fun DataResult<ValorantApiResponse<List<News>>>.transformToResult(): UseCaseResult =
        when (this) {
            is DataResult.Failure -> UseCaseResult.Failed(exception.localizedMessage.orEmpty())
            is DataResult.Success -> {
                val gameUpdates = value.data ?: emptyList()
                UseCaseResult.Success(gameUpdates)
            }
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val news: List<News>) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}