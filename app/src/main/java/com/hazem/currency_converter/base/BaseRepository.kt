package com.hazem.currency_converter.base

import com.hazem.currency_converter.data.remote.currency.models.ServerError
import com.hazem.currency_converter.utils.coroutines.ContextProviders
import com.hazem.currency_converter.utils.network.ApplicationException
import com.hazem.currency_converter.utils.network.ErrorType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository(private val contextProviders: ContextProviders) {

    suspend fun <T> launchBlock(block: suspend CoroutineScope.() -> Response<T>): T {
        return withContext(contextProviders.IO) {
            return@withContext handleResult(block.invoke(this))
        }
    }

    private fun <T> handleResult(response: Response<T>): T {
        return when (response.code()) {
            in 1..399 -> response.body()!!
            401 -> throw ApplicationException(
                type = ErrorType.Network.Unauthorized,
                errorMessage = getErrorMessage(response)
            )
            404 -> throw ApplicationException(
                type = ErrorType.Network.ResourceNotFound,
                errorMessage = getErrorMessage(response)
            )
            else -> throw getApplicationException(response)
        }
    }

    // example of custom error where back end sends extra data with error to be displayed
    private fun <T> getApplicationException(response: Response<T>):ApplicationException {
        // we can add more customization to extract more info from error response as designed with back end team
        return ApplicationException(
            type = ErrorType.Network.Unexpected,
            errorMessage = getErrorMessage(response)
        )
    }

    private fun <T> getErrorMessage(response: Response<T>): String {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ServerError> = moshi.adapter(ServerError::class.java)
        val serverError = jsonAdapter.fromJson(response.errorBody()?.string()?:"")
        return serverError?.message?:"Network Error"
    }
}