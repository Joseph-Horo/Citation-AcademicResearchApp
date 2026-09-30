package com.example.watchlist.data.repository

import com.example.core.database.CitationDatabase
import com.example.watchlist.data.mapper.toWatchList
import com.example.watchlist.data.mapper.toWatchListEntity
import com.example.watchlist.domain.model.WatchList
import com.example.watchlist.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchListRepositoryImpl @Inject constructor(
    private val db: CitationDatabase
): WatchListRepository{
    private val watchListDao = db.watchListDao
    override fun getWatchList(): Flow<List<WatchList>> {
        return watchListDao.getWatchList().map { list->
            list.map { it.toWatchList() }
        }
    }

    override suspend fun removeFromWatchList(watchList: WatchList) {
      watchListDao.removeWatchList(watchList.toWatchListEntity())
    }
}