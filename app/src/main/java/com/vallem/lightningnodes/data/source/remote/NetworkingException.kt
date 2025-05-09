package com.vallem.lightningnodes.data.source.remote

abstract class NetworkingException(override val message: String) : Exception(message)

class ResourceNotFoundException(
    message: String = "We couldn't find what you're looking for"
) : NetworkingException(message)

class ForbiddenResourceException(
    message: String = "We're sorry... You're not allowed to access this!"
) : NetworkingException(message)

class ServerSideException(
    message: String = "Unfortunately, an error has occurred in our systems. Please, try again in a while"
) : NetworkingException(message)

class UnknownNetworkingException(
    message: String = "An unknown error has occurred. Please, try again later"
) : NetworkingException(message)
