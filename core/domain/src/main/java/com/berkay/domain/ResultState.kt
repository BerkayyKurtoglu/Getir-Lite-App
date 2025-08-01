package com.berkay.domain

sealed interface ResultState<out T> {
    data class Success<out T>(val result: T) : ResultState<T>
    data class Error(val message: String) : ResultState<Nothing>
}

suspend fun <T> ResultState<T>.onSuccess(block: suspend (data: T) -> Unit): ResultState<T> {
    if (this is ResultState.Success) {
        block(this.result)
    }
    return this
}

suspend fun <T> ResultState<T>.onError(
    block: suspend (message: String) -> Unit,
): ResultState<T> {
    if (this is ResultState.Error) {
        block(this.message)
    }
    return this
}

inline fun <T, R> ResultState<T>.mapOnSuccess(map: (T) -> R): ResultState<R> = when (this) {
    is ResultState.Success -> ResultState.Success(map(result))
    is ResultState.Error -> this
}