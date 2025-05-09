package com.vallem.lightningnodes.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.domain.model.NodeLocation
import com.vallem.lightningnodes.presentation.theme.LightningNodesTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.math.BigDecimal
import java.time.LocalDateTime

@Composable
fun NodesList(nodes: ImmutableList<Node>, listState: LazyListState, modifier: Modifier = Modifier) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(24.dp),
        modifier = modifier
    ) {
        items(
            items = nodes,
            key = { it.publicKey },
            contentType = { "NODE" },
        ) { node ->
            NodeCard(node = node, modifier = Modifier.fillMaxWidth())
        }

        item {
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun NodesListSkeleton(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .matchParentSize()
                .padding(24.dp)
        ) {
            repeat(SKELETON_NODES_COUNT) {
                NodeSkeleton(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview
@Composable
private fun NodesListSkeletonPreview() {
    LightningNodesTheme {
        NodesListSkeleton(modifier = Modifier.fillMaxSize())
    }
}

private const val SKELETON_NODES_COUNT = 5

@Preview
@Composable
private fun NodesListPreview() {
    val nodes = remember {
        persistentListOf(
            Node(
                "public key",
                "alias",
                100,
                BigDecimal.TEN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                NodeLocation("City", "Country")
            ),
            Node(
                "public key",
                "alias",
                100,
                BigDecimal.TEN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                NodeLocation(null, "Country")
            ),
            Node(
                "public key",
                "alias",
                100,
                BigDecimal.TEN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                NodeLocation("City", null)
            ),
            Node(
                "public key",
                "alias",
                100,
                BigDecimal.TEN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                NodeLocation(null, null)
            ),
        )
    }

    LightningNodesTheme {
        NodesList(
            nodes = nodes,
            listState = rememberLazyListState(),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
        )
    }
}