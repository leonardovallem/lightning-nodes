package com.vallem.lightningnodes.presentation.screen.home

import assertk.assertThat
import assertk.assertions.isInstanceOf
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.domain.repository.NodeRepository
import com.vallem.lightningnodes.presentation.util.UiState
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    @RelaxedMockK
    private lateinit var nodeRepository: NodeRepository

    private lateinit var sut: HomeViewModel

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        sut = HomeViewModel(nodeRepository, dispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when sut is initialized, then data is retrieved`() {
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { nodeRepository.retrieveNodes() }
    }

    @Test
    fun `given data retrieval failure, when refresh is called, then uiState is updated with failure`() {
        coEvery { nodeRepository.retrieveNodes() } returns Result.failure(mockk())

        sut.refresh()
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(sut.uiState.value).isInstanceOf<UiState.Failure>()
    }

    @Test
    fun `given data retrieval success, when refresh is called, then uiState is updated with success`() {
        coEvery { nodeRepository.retrieveNodes() } returns Result.success(persistentListOf())

        sut.refresh()
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(sut.uiState.value).isInstanceOf<UiState.Success<ImmutableList<Node>>>()
    }
}