package com.example.watchlist.presentation

import com.example.watchlist.domain.model.WatchList

data class WatchListState(
    val watchList: List<WatchList> = emptyList()
)
