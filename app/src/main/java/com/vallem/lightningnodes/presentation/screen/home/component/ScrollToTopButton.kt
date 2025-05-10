package com.vallem.lightningnodes.presentation.screen.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vallem.lightningnodes.presentation.theme.LightningNodesTheme
import kotlinx.coroutines.delay

private const val LAST_ANIMATION_DELAY = 300
private const val BUTTON_SCALE_FACTOR = .8f

@Composable
fun ScrollToTopButton(
    isVisible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { 2 * it } + scaleIn(
            animationSpec = tween(delayMillis = LAST_ANIMATION_DELAY),
            initialScale = BUTTON_SCALE_FACTOR
        ),
        exit = scaleOut(targetScale = BUTTON_SCALE_FACTOR) + slideOutVertically(
            animationSpec = tween(delayMillis = LAST_ANIMATION_DELAY)
        ) { 2 * it },
        modifier = modifier
    ) {
        FilledTonalButton(
            onClick = onClick,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(text = "Go to top")
            Spacer(modifier = Modifier.width(12.dp))
            Icon(imageVector = Icons.Rounded.ArrowUpward, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun ScrollToTopButtonPreview() {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        delay(2000)
        isVisible = !isVisible
    }

    LightningNodesTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(100.dp)
                .width(300.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
        ) {
            ScrollToTopButton(
                isVisible = isVisible,
                onClick = {},
            )
        }
    }
}