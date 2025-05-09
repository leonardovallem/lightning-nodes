package com.vallem.lightningnodes.presentation.screen.home.component

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vallem.lightningnodes.TestApplication
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.domain.model.NodeLocation
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class)
class NodeCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given no city and no country, when NodeCard is rendered, then no location related component should be rendered`() {
        val node = mockk<Node>(relaxed = true) {
            every { location } returns NodeLocation(null, null)
        }

        composeTestRule.setContent {
            NodeCard(node)
        }

        composeTestRule.onNodeWithTag("node-location-information").assertDoesNotExist()
    }

    @Test
    fun `given city but no country, when NodeCard is rendered, then location comma separator should not be rendered`() {
        val node = mockk<Node>(relaxed = true) {
            every { location } returns NodeLocation("City", null)
        }

        composeTestRule.setContent {
            NodeCard(node)
        }

        composeTestRule.onNodeWithTag("node-location-information")
            .onChildren()
            .onLast()
            .assertTextEquals("City")
    }

    @Test
    fun `given country but no city, when NodeCard is rendered, then location comma separator should not be rendered`() {
        val node = mockk<Node>(relaxed = true) {
            every { location } returns NodeLocation(null, "Country")
        }

        composeTestRule.setContent {
            NodeCard(node)
        }

        composeTestRule.onNodeWithTag("node-location-information")
            .onChildren()
            .onLast()
            .assertTextEquals("Country")
    }

    @Test
    fun `given city and country, when NodeCard is rendered, then location comma separator should be rendered`() {
        val node = mockk<Node>(relaxed = true) {
            every { location } returns NodeLocation("City", "Country")
        }

        composeTestRule.setContent {
            NodeCard(node)
        }

        composeTestRule.onNodeWithTag("node-location-information")
            .onChildren()
            .onLast()
            .assertTextEquals("City, Country")
    }
}