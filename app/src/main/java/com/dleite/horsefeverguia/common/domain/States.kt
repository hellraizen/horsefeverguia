package com.dleite.horsefeverguia.common.domain

sealed class States<out T> {
    data class Success<T>(val data: T) : States<T>()
    data class Failure(val error: Error) : States<Nothing>()
}
