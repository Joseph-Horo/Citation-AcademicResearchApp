package com.example.details.domain.repository

import com.example.core.resource.Resource
import com.example.details.domain.model.Detail
import com.example.details.domain.model.WatchList
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getDetails(
        fetchFromRemote: Boolean,
        id: String
    ): Flow<Resource<Detail>>
    suspend fun removeFromWatchList(watchList: WatchList)
    suspend fun addToWatchList(watchList: WatchList)
    fun isInWatchList(id: String): Flow<Boolean>
}