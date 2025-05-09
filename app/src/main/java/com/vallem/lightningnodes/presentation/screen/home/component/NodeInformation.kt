package com.vallem.lightningnodes.presentation.screen.home.component

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CurrencyBitcoin
import androidx.compose.material.icons.rounded.Webhook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vallem.lightningnodes.domain.model.BitCoinCount
import com.vallem.lightningnodes.domain.model.NodeLocation
import com.vallem.lightningnodes.presentation.screen.home.component.NodeInformation.Capacity
import com.vallem.lightningnodes.presentation.screen.home.component.NodeInformation.Channels
import com.vallem.lightningnodes.presentation.screen.home.component.NodeInformation.FirstSeenDate
import com.vallem.lightningnodes.presentation.screen.home.component.NodeInformation.Location
import com.vallem.lightningnodes.presentation.screen.home.component.NodeInformation.UpdateDate
import com.vallem.lightningnodes.presentation.screen.home.modifier.shimmer
import com.vallem.lightningnodes.presentation.util.extension.toHumanReadableFormat
import java.time.LocalDateTime

@Composable
fun NodeInformation(information: NodeInformation, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(imageVector = information.icon, contentDescription = null)

        Text(
            text = information.text,
            style = information.textStyle,
            overflow = TextOverflow.MiddleEllipsis,
            maxLines = 1,
        )
    }
}

@Composable
fun NodeInformationSkeleton(
    modifier: Modifier = Modifier,
    transition: InfiniteTransition = rememberInfiniteTransition(label = ""),
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30))
                .shimmer(transition = transition)
                .size(24.dp)
        )

        Box(
            modifier = Modifier
                .shimmer(transition = transition)
                .height(20.dp)
                .weight(1f)
        )
    }
}

sealed class NodeInformation(val icon: ImageVector, val text: AnnotatedString) {
    data class Channels(val count: Int) : NodeInformation(
        icon = Icons.Rounded.Webhook,
        text = buildAnnotatedString {
            append(count.toString())
            append(" channels")
        },
    )

    data class Capacity(val count: BitCoinCount) : NodeInformation(
        icon = Icons.Rounded.CurrencyBitcoin,
        text = buildAnnotatedString {
            append(count.toString())
            append(" capacity")
        },
    )

    data class Location(val location: NodeLocation) : NodeInformation(
        icon = Icons.Outlined.Place,
        text = buildAnnotatedString {
            if (location.city != null) {
                append(location.city)
                if (location.country != null) append(", ")
            }

            if (location.country != null) append(location.country)
        }
    )

    data class FirstSeenDate(val time: LocalDateTime) : NodeInformation(
        icon = Icons.Rounded.CalendarMonth,
        text = buildAnnotatedString {
            append("First seen at ")
            append(time.toHumanReadableFormat())
        }
    )

    data class UpdateDate(val time: LocalDateTime) : NodeInformation(
        icon = Icons.Rounded.Autorenew,
        text = buildAnnotatedString {
            append("Last updated at ")
            append(time.toHumanReadableFormat())
        }
    )
}

private val NodeInformation.textStyle: TextStyle
    @Composable get() = when (this) {
        is Channels, is Capacity -> MaterialTheme.typography.bodyLarge
        is Location -> MaterialTheme.typography.bodyMedium
        is FirstSeenDate, is UpdateDate -> MaterialTheme.typography.bodySmall
    }
