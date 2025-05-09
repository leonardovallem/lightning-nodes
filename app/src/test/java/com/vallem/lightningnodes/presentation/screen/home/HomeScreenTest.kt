package com.vallem.lightningnodes.presentation.screen.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vallem.lightningnodes.TestApplication
import com.vallem.lightningnodes.data.source.remote.UnknownNetworkingException
import com.vallem.lightningnodes.presentation.util.UiState
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given uiState is Idle, when HomeScreen is rendered, then nothing should be rendered`() {
        composeTestRule.setContent {
            HomeScreen(uiState = UiState.Idle, onRefresh = {})
        }

        composeTestRule.onNodeWithTag("crossfade")
            .onChild()
            .assertDoesNotExist()
    }

    @Test
    fun `given uiState is Loading, when HomeScreen is rendered, then only skeleton should be rendered`() {
        composeTestRule.setContent {
            HomeScreen(uiState = UiState.Loading.Refresh, onRefresh = {})
        }

        composeTestRule.onNodeWithTag("nodes-list-skeleton").assertExists()
        composeTestRule.onNodeWithTag("nodes-list").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error-display").assertDoesNotExist()
    }

    @Test
    fun `given uiState is Failure, when HomeScreen is rendered, then only error display should be rendered`() {
        composeTestRule.setContent {
            HomeScreen(uiState = UiState.Failure(UnknownNetworkingException()), onRefresh = {})
        }

        composeTestRule.onNodeWithTag("error-display").assertExists()
        composeTestRule.onNodeWithTag("nodes-list-skeleton").assertDoesNotExist()
        composeTestRule.onNodeWithTag("nodes-list").assertDoesNotExist()
    }

    @Test
    fun `given uiState is Success, when HomeScreen is rendered, then only error display should be rendered`() {
        composeTestRule.setContent {
            HomeScreen(uiState = UiState.Success(persistentListOf()), onRefresh = {})
        }

        composeTestRule.onNodeWithTag("nodes-list").assertExists()
        composeTestRule.onNodeWithTag("nodes-list-skeleton").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error-display").assertDoesNotExist()
    }
}