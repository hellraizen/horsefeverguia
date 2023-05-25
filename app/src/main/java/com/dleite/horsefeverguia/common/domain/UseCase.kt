package com.dleite.horsefeverguia.common.domain


abstract class UseCase<Input, Output> {
    abstract suspend fun execute(vararg params: Param<Input>): States<Output>


}
