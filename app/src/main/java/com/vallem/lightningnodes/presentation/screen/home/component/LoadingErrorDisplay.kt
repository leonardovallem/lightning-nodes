package com.vallem.lightningnodes.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vallem.lightningnodes.data.source.remote.ForbiddenResourceException
import com.vallem.lightningnodes.data.source.remote.NetworkingException
import com.vallem.lightningnodes.data.source.remote.ResourceNotFoundException
import com.vallem.lightningnodes.data.source.remote.ServerSideException
import com.vallem.lightningnodes.data.source.remote.UnknownNetworkingException
import com.vallem.lightningnodes.presentation.theme.LightningNodesTheme

@Composable
fun LoadingErrorDisplay(error: NetworkingException, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Rounded.WarningAmber,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onErrorContainer,
            modifier = Modifier.size(48.dp)
        )

        Text(
            text = error.message,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun LoadingErrorDisplayPreview() {
    val exceptions = remember {
        listOf(
            ResourceNotFoundException(),
            ForbiddenResourceException(),
            ServerSideException(),
            UnknownNetworkingException(),
        )
    }

    LightningNodesTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
        ) {
            exceptions.forEach {
                LoadingErrorDisplay(it, Modifier.fillMaxWidth())
                HorizontalDivider()
            }
        }
    }
}