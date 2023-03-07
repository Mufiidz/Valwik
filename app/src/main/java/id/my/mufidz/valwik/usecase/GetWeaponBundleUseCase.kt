package id.my.mufidz.valwik.usecase

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.BaseUseCase
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.models.ValorantApiResponse
import id.my.mufidz.valwik.models.WeaponBundle
import id.my.mufidz.valwik.utils.DataResult
import timber.log.Timber
import javax.inject.Inject

class GetWeaponBundleUseCase @Inject constructor(private val apiServices: ApiServices) :
    BaseUseCase<Nothing, ValorantApiResponse<List<WeaponBundle>>, GetWeaponBundleUseCase.UseCaseResult>() {
    override suspend fun execute(param: Nothing?): DataResult<ValorantApiResponse<List<WeaponBundle>>> =
        apiServices.getBundles()

    override suspend fun DataResult<ValorantApiResponse<List<WeaponBundle>>>.transformToResult(): UseCaseResult =
        when (this) {
            is DataResult.Failure -> UseCaseResult.Failed(exception.localizedMessage.orEmpty())
            is DataResult.Success -> {
                val weaponBundles = value.data ?: emptyList()
                Timber.d("usecase bundles -> $weaponBundles")
                UseCaseResult.Success(weaponBundles)
            }
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val weaponBundles: List<WeaponBundle>) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}