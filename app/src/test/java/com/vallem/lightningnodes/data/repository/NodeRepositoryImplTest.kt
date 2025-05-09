package com.vallem.lightningnodes.data.repository

import assertk.assertThat
import assertk.assertions.doesNotContain
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import com.vallem.lightningnodes.data.mapper.toDomain
import com.vallem.lightningnodes.data.sampleNodeDto
import com.vallem.lightningnodes.data.source.remote.ConnectivityApi
import com.vallem.lightningnodes.domain.model.Node
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class NodeRepositoryImplTest {
    @MockK
    private lateinit var connectivityApi: ConnectivityApi

    private lateinit var sut: NodeRepositoryImpl

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        sut = NodeRepositoryImpl(connectivityApi, dispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `given api error, when retrieveNodes is called, then should return failure`() =
        runTest(dispatcher) {
            coEvery { connectivityApi.retrieveRanking() } throws HttpException(mockk(relaxed = true))

            val result = sut.retrieveNodes()

            assertThat(result).prop(Result<PersistentList<Node>>::isFailure).isTrue()
        }

    @Test
    fun `given no results, when retrieveNodes is called, then should return success with empty list`() =
        runTest(dispatcher) {
            coEvery { connectivityApi.retrieveRanking() } returns emptyList()

            val result = sut.retrieveNodes()

            assertThat(result.getOrNull()).isNotNull().isEqualTo(emptyList())
        }

    @Test
    fun `given node with invalid field value, when retrieveNodes is called, then that node should not be returned`() =
        runTest(dispatcher) {
            val invalidNode = sampleNodeDto(alias = "Invalid node", publicKey = null)
            coEvery { connectivityApi.retrieveRanking() } returns listOf(
                sampleNodeDto(alias = "Valid node"),
                invalidNode,
            )

            val result = sut.retrieveNodes()

            assertThat(result.getOrNull()).isNotNull().doesNotContain(invalidNode.toDomain())
        }

    @Test
    fun `given only valid nodes, when retrieveNodes is called, then all received nodes should be returned`() =
        runTest(dispatcher) {
            val apiResponse = listOf(sampleNodeDto(alias = "Node 1"), sampleNodeDto(alias = "Node 2"))
            coEvery { connectivityApi.retrieveRanking() } returns apiResponse

            val result = sut.retrieveNodes()

            assertThat(result.getOrNull()).isNotNull().hasSize(apiResponse.size)
        }
}