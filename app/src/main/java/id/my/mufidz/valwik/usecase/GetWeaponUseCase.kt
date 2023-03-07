package id.my.mufidz.valwik.usecase

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.BaseUseCase
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.models.ValorantApiResponse
import id.my.mufidz.valwik.models.weapon.Weapon
import id.my.mufidz.valwik.utils.DataResult
import javax.inject.Inject

class GetWeaponUseCase @Inject constructor(private val apiServices: ApiServices) :
    BaseUseCase<Nothing, ValorantApiResponse<List<Weapon>>, GetWeaponUseCase.UseCaseResult>() {
    override suspend fun execute(param: Nothing?): DataResult<ValorantApiResponse<List<Weapon>>> =
        apiServices.getWeapons()

    override suspend fun DataResult<ValorantApiResponse<List<Weapon>>>.transformToResult(): UseCaseResult =
        when (this) {
            is DataResult.Failure -> UseCaseResult.Failed(exception.localizedMessage.orEmpty())
            is DataResult.Success -> {
                val weapons = value.data ?: emptyList()
                UseCaseResult.Success(weapons)
            }
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val weapons: List<Weapon>) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}