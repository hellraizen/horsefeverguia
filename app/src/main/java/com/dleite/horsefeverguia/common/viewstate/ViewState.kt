package com.dleite.horsefeverguia.common.viewstate


sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failure(val error: Error) : ViewState<Nothing>()
    object Done : ViewState<Nothing>()
}
