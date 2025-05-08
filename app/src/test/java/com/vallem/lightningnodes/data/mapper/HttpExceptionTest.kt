package com.vallem.lightningnodes.data.mapper

import assertk.assertThat
import assertk.assertions.isInstanceOf
import com.vallem.lightningnodes.data.source.remote.ForbiddenResourceException
import com.vallem.lightningnodes.data.source.remote.ResourceNotFoundException
import com.vallem.lightningnodes.data.source.remote.ServerSideException
import com.vallem.lightningnodes.data.source.remote.UnknownNetworkingException
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class HttpExceptionTest {
    @Test
    fun `given 401 response status, when toFriendlyException is called, then should return ForbiddenResourceException`() {
        val exception = HttpException(mockResponse(401))
        val result = exception.toFriendlyException()
        assertThat(result).isInstanceOf(ForbiddenResourceException::class)
    }

    @Test
    fun `given 403 response status, when toFriendlyException is called, then should return ForbiddenResourceException`() {
        val exception = HttpException(mockResponse(403))
        val result = exception.toFriendlyException()
        assertThat(result).isInstanceOf(ForbiddenResourceException::class)
    }

    @Test
    fun `given 404 response status, when toFriendlyException is called, then should return ResourceNotFoundException`() {
        val exception = HttpException(mockResponse(404))
        val result = exception.toFriendlyException()
        assertThat(result).isInstanceOf(ResourceNotFoundException::class)
    }

    @Test
    fun `given any 500 response status, when toFriendlyException is called, then should return ServerSideException`() {
        val exception = HttpException(mockResponse(500))
        val result = exception.toFriendlyException()
        assertThat(result).isInstanceOf(ServerSideException::class)
    }

    @Test
    fun `given any different response status, when toFriendlyException is called, then should return UnknownNetworkingException`() {
        val exception = HttpException(mockResponse(302))
        val result = exception.toFriendlyException()
        assertThat(result).isInstanceOf(UnknownNetworkingException::class)
    }

    private fun mockResponse(statusCode: Int) = mockk<Response<*>>(relaxed = true) {
        every { code() } returns statusCode
    }
}