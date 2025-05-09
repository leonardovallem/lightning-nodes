package com.vallem.lightningnodes.presentation.screen.home.component

import android.content.ClipData
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.domain.model.NodeLocation
import com.vallem.lightningnodes.presentation.screen.home.modifier.shimmer
import com.vallem.lightningnodes.presentation.theme.LightningNodesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Composable
fun NodeCard(node: Node, modifier: Modifier = Modifier) {
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope { Dispatchers.IO }

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = node.alias,
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = node.publicKey,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.MiddleEllipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        scope.launch {
                            val clipData = ClipData.newPlainText("Node public key", node.publicKey)
                            clipboard.setClipEntry(ClipEntry(clipData))
                        }
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ContentCopy,
                        contentDescription = "Copy to clipboard",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            NodeInformation(
                information = NodeInformation.Channels(node.channels),
                modifier = Modifier.fillMaxWidth()
            )

            NodeInformation(
                information = NodeInformation.Capacity(node.capacity),
                modifier = Modifier.fillMaxWidth()
            )

            if (!node.location.isUnknown) NodeInformation(
                information = NodeInformation.Location(node.location),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .testTag("node-location-information")
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            NodeInformation(
                information = NodeInformation.FirstSeenDate(node.firstSeen),
                modifier = Modifier.fillMaxWidth()
            )

            NodeInformation(
                information = NodeInformation.UpdateDate(node.updatedAt),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun NodeSkeleton(
    modifier: Modifier = Modifier,
    transition: InfiniteTransition = rememberInfiniteTransition(label = "")
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(CardDefaults.cardColors().containerColor)
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .shimmer(transition = transition)
                .height(24.dp)
                .fillMaxWidth(.75f)
        )

        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .shimmer(transition = transition)
                .height(18.dp)
                .fillMaxWidth()
        )

        repeat(2) {
            NodeInformationSkeleton(
                transition = transition,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        repeat(2) {
            NodeInformationSkeleton(
                transition = transition,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun NodeComponentPreview() {
    val node = remember {
        Node(
            "035e4ff418fc8b5554c5d9eea66396c227bd429a3251c8cbc711002ba215bfc226",
            "WalletOfSatoshi.com",
            2772,
            BigDecimal(154.645032),
            LocalDateTime.ofInstant(Instant.ofEpochMilli(1601429940), ZoneOffset.systemDefault()),
            LocalDateTime.ofInstant(Instant.ofEpochMilli(1661812116), ZoneOffset.systemDefault()),
            location = NodeLocation("Vancouver", "Canada")
        )
    }

    LightningNodesTheme {
        NodeCard(
            node = node,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
        )
    }
}

@Preview
@Composable
private fun NodeSkeletonPreview() {
    LightningNodesTheme {
        NodeSkeleton(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
        )
    }
}