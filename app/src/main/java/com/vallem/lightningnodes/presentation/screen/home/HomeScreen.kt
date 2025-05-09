package com.vallem.lightningnodes.presentation.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.vallem.lightningnodes.data.source.remote.NetworkingException
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.presentation.screen.home.component.LoadingErrorDisplay
import com.vallem.lightningnodes.presentation.screen.home.component.NodesList
import com.vallem.lightningnodes.presentation.screen.home.component.NodesListSkeleton
import com.vallem.lightningnodes.presentation.theme.LightningNodesTheme
import com.vallem.lightningnodes.presentation.util.UiState

@Composable
fun HomeScreen(uiState: UiState<List<Node>>, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier) { pv ->
        Crossfade(
            targetState = uiState,
            modifier = Modifier
                .padding(pv)
                .testTag("crossfade")
        ) {
            when (it) {
                is UiState.Success<List<Node>> -> NodesList(
                    nodes = it.data,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("nodes-list")
                )

                is UiState.Failure -> LoadingErrorDisplay(
                    error = it.throwable as NetworkingException,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("error-display")
                )

                UiState.Loading -> NodesListSkeleton(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("nodes-list-skeleton")
                )

                UiState.Idle -> Unit
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    LightningNodesTheme {
        HomeScreen(UiState.Idle)
    }
}