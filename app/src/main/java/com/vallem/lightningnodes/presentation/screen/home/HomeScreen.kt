package com.vallem.lightningnodes.presentation.screen.home

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vallem.lightningnodes.data.source.remote.NetworkingException
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.presentation.screen.home.component.LoadingErrorDisplay
import com.vallem.lightningnodes.presentation.screen.home.component.NodesList
import com.vallem.lightningnodes.presentation.screen.home.component.NodesListSkeleton
import com.vallem.lightningnodes.presentation.theme.LightningNodesTheme
import com.vallem.lightningnodes.presentation.util.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(uiState = uiState, onRefresh = viewModel::refresh)
}

@OptIn(ExperimentalMaterial3Api::class)
@VisibleForTesting
@Composable
fun HomeScreen(uiState: UiState<List<Node>>, onRefresh: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier) { pv ->
        PullToRefreshBox(
            isRefreshing = uiState == UiState.Loading.Refresh,
            onRefresh = onRefresh,
            modifier = Modifier
                .padding(pv)
                .fillMaxSize()
        ) {
            Crossfade(
                targetState = uiState,
                modifier = Modifier
                    .matchParentSize()
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

                    is UiState.Loading -> NodesListSkeleton(
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("nodes-list-skeleton")
                    )

                    UiState.Idle -> Unit
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    LightningNodesTheme {
        HomeScreen(UiState.Idle, {})
    }
}