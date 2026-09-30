package com.example.explore.domain.repository

import com.example.core.resource.Resource
import com.example.explore.domain.model.WorkResult
import kotlinx.coroutines.flow.Flow

interface ExploreRepository {
    suspend fun getWorks(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<WorkResult>>>
}