package id.my.mufidz.valwik.models

import kotlinx.serialization.Serializable

@Serializable
data class ValorantApiResponse<T>(
    val status : Int = 0,
    override val data : T? = null,
    override val error: String = "",
    val errors: List<ErrorResponse> = emptyList()
) : BaseResponse<T>

@Serializable
data class ErrorResponse(
    val message: String = "",
    val code: Int = 0,
    val details: String = ""
)

interface BaseResponse<T> {
    val data: T?
    val error: String
}