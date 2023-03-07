package id.my.mufidz.valwik.utils

import id.my.mufidz.valwik.models.BaseResponse
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend inline fun <reified T, reified Response : BaseResponse<T>> HttpResponse.awaitResults(): DataResult<Response> {
    return try {
        var result: DataResult<Response>
        body<Response>().also {
            result = if (status == HttpStatusCode.OK) {
                if (it.data != null) {
                    DataResult.Success(it)
                } else {
                    DataResult.Failure(Exception("Null Data"))
                }
            } else {
                DataResult.Failure(Exception(it.error))
            }
        }
        result
    } catch (e: NoTransformationFoundException) {
        DataResult.Failure(Exception("Empty Response"))
    } catch (e: Exception) {
        DataResult.Failure(e)
    }
}