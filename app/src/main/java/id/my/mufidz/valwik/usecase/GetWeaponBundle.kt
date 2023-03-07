package id.my.mufidz.valwik.usecase

import id.my.mufidz.valwik.base.ActionResult
import id.my.mufidz.valwik.base.BaseUseCase
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.models.ValorantApiResponse
import id.my.mufidz.valwik.models.weapon.WeaponSkin
import id.my.mufidz.valwik.utils.DataResult
import javax.inject.Inject

class GetWeaponBundle @Inject constructor(private val apiServices: ApiServices) :
    BaseUseCase<String, ValorantApiResponse<List<WeaponSkin>>, GetWeaponBundle.UseCaseResult>() {

    private var query: String = ""

    override suspend fun execute(param: String?): DataResult<ValorantApiResponse<List<WeaponSkin>>> =
        if (param.isNullOrEmpty()) {
            DataResult.Failure(Exception("Empty Title Weapon Bundle"))
        } else {
            query = param
            apiServices.getWeaponSkins()
        }

    override suspend fun DataResult<ValorantApiResponse<List<WeaponSkin>>>.transformToResult(): UseCaseResult =
        when (this) {
            is DataResult.Failure -> UseCaseResult.Failed(exception.localizedMessage.orEmpty())
            is DataResult.Success -> {
                var listWeaponBundle = value.data ?: emptyList()
                listWeaponBundle = if (query.isEmpty()) emptyList() else listWeaponBundle.filter {
                    it.displayName.startsWith(
                        query,
                        true
                    )
                }
                UseCaseResult.Success(listWeaponBundle)
            }
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val weaponBundles: List<WeaponSkin>) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}