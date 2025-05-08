package com.vallem.lightningnodes.data.mapper

import com.vallem.lightningnodes.data.source.remote.ForbiddenResourceException
import com.vallem.lightningnodes.data.source.remote.ResourceNotFoundException
import com.vallem.lightningnodes.data.source.remote.ServerSideException
import com.vallem.lightningnodes.data.source.remote.UnknownNetworkingException
import retrofit2.HttpException

fun HttpException.toFriendlyException() = when (response()?.code()) {
    401, 403 -> ForbiddenResourceException("User not allowed to consume this resource")
    404 -> ResourceNotFoundException("Requested resource does not exist")
    in 500..599 -> ServerSideException("An error has occurred on the server side")
    else -> UnknownNetworkingException()
}