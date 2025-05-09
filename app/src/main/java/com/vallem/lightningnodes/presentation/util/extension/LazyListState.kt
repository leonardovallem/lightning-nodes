package com.vallem.lightningnodes.presentation.util.extension

import androidx.compose.foundation.lazy.LazyListState

private const val MAX_ANIMATED_SCROLL_INDEX = 30

suspend fun LazyListState.fastScrollToTop() {

    if (firstVisibleItemIndex > MAX_ANIMATED_SCROLL_INDEX) scrollToItem(MAX_ANIMATED_SCROLL_INDEX)
    animateScrollToItem(0)
}

val LazyListState.isScrolledPastTop
    get() = firstVisibleItemIndex > 0