package com.example.watchlist.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.domain.model.WatchList
import com.example.watchlist.domain.repository.WatchListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WatchListViewModel @Inject constructor(
    private val repository: WatchListRepository
): ViewModel(){
    var state by mutableStateOf(WatchListState())
        private set
    init {
        viewModelScope.launch {
            repository.getWatchList()
                .collect { watchList->
                    state = state.copy(
                       watchList = watchList
                    )
                }
        }
    }
    fun removeFromWatchList(watchList: WatchList){
        viewModelScope.launch {
            repository.removeFromWatchList(watchList)
        }
    }

    }
