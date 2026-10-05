package com.example.home.domain.repository

import com.example.core.resource.Resource
import com.example.home.domain.model.CitedResult
import com.example.home.domain.model.WorkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {


    suspend fun getRecentWorks(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<WorkResult>>>

    suspend fun getMostCitedWork(
        fetchFromRemote: Boolean
    ):Flow<Resource<List<CitedResult>>>


}