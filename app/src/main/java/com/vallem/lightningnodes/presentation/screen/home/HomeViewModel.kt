package com.vallem.lightningnodes.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.domain.repository.NodeRepository
import com.vallem.lightningnodes.presentation.util.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val nodeRepository: NodeRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Node>>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    private var refreshJob: Job? = null

    init {
        refresh(UiState.Loading.Initial)
    }

    fun refresh(loading: UiState.Loading = UiState.Loading.Refresh) {
        refreshJob?.cancel()

        refreshJob = viewModelScope.launch(coroutineDispatcher) {
            _uiState.value = loading

            nodeRepository.retrieveNodes()
                .onSuccess {
                    println("success = $it")
                    _uiState.value = UiState.Success(it)
                }
                .onFailure {
                    println("failure = $it")
                    _uiState.value = UiState.Failure(it)
                }
        }
    }
}