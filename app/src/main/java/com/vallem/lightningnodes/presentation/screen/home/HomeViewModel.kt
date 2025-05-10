package com.vallem.lightningnodes.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vallem.lightningnodes.data.source.remote.UnknownNetworkingException
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.domain.repository.NodeRepository
import com.vallem.lightningnodes.presentation.util.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val nodeRepository: NodeRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<ImmutableList<Node>>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    private var refreshJob: Job? = null
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = UiState.Failure(UnknownNetworkingException())
    }

    init {
        refresh(UiState.Loading.Initial)
    }

    fun refresh(loading: UiState.Loading = UiState.Loading.Refresh) {
        refreshJob?.cancel()

        refreshJob = viewModelScope.launch(coroutineDispatcher + coroutineExceptionHandler) {
            _uiState.value = loading

            nodeRepository.retrieveNodes()
                .onSuccess { _uiState.value = UiState.Success(it) }
                .onFailure { _uiState.value = UiState.Failure(it) }
        }
    }
}