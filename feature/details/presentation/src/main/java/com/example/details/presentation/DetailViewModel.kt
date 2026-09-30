package com.example.details.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.di.ViewModelAssistedFactory
import com.example.core.resource.Resource
import com.example.details.data.mapper.toWatchlist
import com.example.details.domain.repository.DetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    private val repository: DetailRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    @AssistedFactory
    interface Factory: ViewModelAssistedFactory<DetailViewModel>{
        override fun create(savedStateHandle: SavedStateHandle): DetailViewModel
    }

    var state by mutableStateOf(DetailState())
        private set

    init {
        getDetails()
        isInWatchList()
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.Refresh -> {
                   getDetails(fetchFromRemote = true)
            }
        }
    }

    private fun getDetails(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("id") ?: return@launch
            val workId = id.substringAfterLast("/")

            state = state.copy(
                isLoading = true
            )
            repository.getDetails(fetchFromRemote,workId)
                .collect { detail->
                    when(detail){
                        is Resource.Error<*> -> Unit
                        is Resource.Loading<*> -> {
                            state = state.copy(
                                isLoading = detail.isLoading
                            )
                        }
                        is Resource.Success<*> -> {
                            detail.data?.let { detail->
                                state = state.copy(
                                    detail = detail,
                                    isLoading = false
                                )

                            }

                        }
                    }
                }
        }
    }

    private fun isInWatchList() {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("id")?: return@launch
            repository.isInWatchList(id)
                .collect { saved ->
                    state = state.copy(
                        isInWatchList = saved
                    )
                }
        }
    }

    fun addToWatchList() {
        state.detail?.let { detail ->
            viewModelScope.launch {
                repository.addToWatchList(detail.toWatchlist())
            }
        }
    }

    fun removeFromWatchList() {
        state.detail?.let { detail ->
            viewModelScope.launch {
                repository.removeFromWatchList(detail.toWatchlist())
            }
        }
    }
}