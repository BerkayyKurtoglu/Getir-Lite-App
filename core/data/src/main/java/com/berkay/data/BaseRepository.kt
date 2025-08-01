package com.berkay.data

import com.berkay.domain.ResultState
import com.berkay.network.extensions.toResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

const val GENERIC_ERROR = "Something went wrong"

open class BaseRepository {

    suspend inline fun <T : Any> callNetwork(
        crossinline call: suspend () -> Response<T>
    ): ResultState<T> = withContext(Dispatchers.IO) {
        try {
            call.invoke().toResultState()
        } catch (exception: Exception) {
            ResultState.Error(message = exception.message ?: GENERIC_ERROR)
        }
    }

}
