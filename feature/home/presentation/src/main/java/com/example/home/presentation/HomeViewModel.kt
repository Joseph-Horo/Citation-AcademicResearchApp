package com.example.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.resource.Resource
import com.example.home.domain.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {
    var state by mutableStateOf(HomeState())
        private set
    init {
        getRecentWorks()
        getMostCitedWorks()

    }
    fun onEvent(event: HomeEvent){
        when(event){
            HomeEvent.Refresh -> {
             getRecentWorks(fetchFromRemote = true)
                getMostCitedWorks(fetchFromRemote = true)
            }
        }
    }


    private fun getRecentWorks(fetchFromRemote: Boolean = false){
        viewModelScope.launch {
            repository.getRecentWorks(fetchFromRemote)
                .collect { works->
                    when(works){
                        is Resource.Error<*> -> Unit
                        is Resource.Loading<*> -> {
                            state = state.copy(
                                isLoading = works.isLoading
                            )
                        }
                        is Resource.Success<*> ->{
                            works.data?.let{works->
                                state = state.copy(
                                    recentWorks = works
                                        .take(5),
                                    isLoading = false

                                )

                            }
                        }
                    }
                }
        }

    }
    private fun getMostCitedWorks(fetchFromRemote: Boolean = false){
        viewModelScope.launch {
            repository.getMostCitedWork(fetchFromRemote)
                .collect { cited->
                    when(cited){
                        is Resource.Error<*> -> Unit
                        is Resource.Loading<*> -> {
                            state = state.copy(
                                isLoading = cited.isLoading
                            )
                        }
                        is Resource.Success<*> ->{
                            cited.data?.let{works->
                                state = state.copy(
                                    citedWorks = works
                                        .take(5),
                                    isLoading = false

                                )

                            }
                        }
                    }
                }
        }

    }

}