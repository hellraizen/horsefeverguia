package com.dleite.horsefeverguia.common.domain

import java.net.HttpURLConnection

sealed class Error(val code: String, val message: String, val title: String) {
    open class Feature(code: String, message: String, title: String) : Error(code, message, title)
    open class Network(code: String, message: String, title: String) : Error(code, message, title)
    open class Local(code: String, message: Int, title: Int) : Error(
        code = code,
        message = message.toString(),
        title = title.toString()
    )
}

data class GatewayTimeout(
    val errorTitle: String = "Ops!! Ocorreu algum errado.",
    val errorCode: String = HttpURLConnection.HTTP_GATEWAY_TIMEOUT.toString(),
    val errorMessage: String = "Sem conexão com a internet!"
) : Error.Network(errorCode, errorMessage, errorTitle)
