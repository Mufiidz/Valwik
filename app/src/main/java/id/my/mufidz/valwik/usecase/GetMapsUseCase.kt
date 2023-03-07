package id.my.mufidz.valwik.usecase

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.BaseUseCase
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.models.Maps
import id.my.mufidz.valwik.models.ValorantApiResponse
import id.my.mufidz.valwik.utils.DataResult
import javax.inject.Inject

class GetMapsUseCase @Inject constructor(private val apiServices: ApiServices) :
    BaseUseCase<Nothing, ValorantApiResponse<List<Maps>>, GetMapsUseCase.UseCaseResult>() {
    override suspend fun execute(param: Nothing?): DataResult<ValorantApiResponse<List<Maps>>> =
        apiServices.getMaps()

    override suspend fun DataResult<ValorantApiResponse<List<Maps>>>.transformToResult(): UseCaseResult =
        when (this) {
            is DataResult.Failure -> UseCaseResult.Failed(exception.localizedMessage.orEmpty())
            is DataResult.Success -> {
                val maps = value.data ?: emptyList()
                UseCaseResult.Success(maps)
            }
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val maps: List<Maps>) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}