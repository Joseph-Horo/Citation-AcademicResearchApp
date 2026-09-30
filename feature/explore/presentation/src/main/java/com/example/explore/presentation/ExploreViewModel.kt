package com.example.explore.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.resource.Resource
import com.example.explore.domain.repository.ExploreRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class ExploreViewModel @Inject constructor(
    private val repository: ExploreRepository
): ViewModel() {
    var state by mutableStateOf(ExploreState())
        private set
    private var searchJob: Job? = null
    init {
        getWorks()
    }
    fun onEvent(event: ExploreEvent){
        when(event){
            is ExploreEvent.OnSearchQueryChange -> {
                state = state.copy(
                    searchQuery = event.query
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500.milliseconds)
                    getWorks()
                }
            }
            ExploreEvent.Refresh -> getWorks(fetchFromRemote = true)
        }
    }

    private fun getWorks(
        fetchFromRemote: Boolean = false,
        query: String = state.searchQuery.lowercase()
        ){
        viewModelScope.launch {
            repository.getWorks(fetchFromRemote, query)
                .collect { works->
                    when(works){
                        is Resource.Error<*> -> Unit
                        is Resource.Loading<*> -> {
                            state = state.copy(
                                isLoading = works.isLoading
                            )
                        }
                        is Resource.Success<*> -> {
                            works.data?.let { works->
                                state = state.copy(
                                    works = works,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }

    }
}