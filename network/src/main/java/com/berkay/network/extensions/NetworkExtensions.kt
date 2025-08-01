package com.berkay.network.extensions

import com.berkay.domain.ResultState
import retrofit2.Response

fun <T> Response<T>.toResultState(): ResultState<T> {
    return if (isSuccessful) {
        body()?.let { data ->
            ResultState.Success(data)
        } ?: run {
            ResultState.Error(message())
        }
    } else {
        ResultState.Error(message())
    }
}
