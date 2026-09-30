package com.example.watchlist.domain.repository

import com.example.watchlist.domain.model.WatchList
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {
    fun getWatchList(): Flow<List<WatchList>>
    suspend fun removeFromWatchList(watchList: WatchList)
}