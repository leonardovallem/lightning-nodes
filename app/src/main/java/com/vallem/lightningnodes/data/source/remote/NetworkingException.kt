package com.vallem.lightningnodes.data.source.remote

abstract class NetworkingException(message: String?) : Exception(message)

class ResourceNotFoundException(message: String? = null) : NetworkingException(message)

class ForbiddenResourceException(message: String? = null) : NetworkingException(message)

class ServerSideException(message: String? = null) : NetworkingException(message)

class UnknownNetworkingException(message: String? = null) : NetworkingException(message)
