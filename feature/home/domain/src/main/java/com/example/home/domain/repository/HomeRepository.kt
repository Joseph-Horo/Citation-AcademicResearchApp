package com.example.home.domain.repository

import com.example.core.resource.Resource
import com.example.home.domain.model.CitedResult
import com.example.home.domain.model.InstitutionResult
import com.example.home.domain.model.Research
import com.example.home.domain.model.WorkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getTopics(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Research>>>

    suspend fun getRecentWorks(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<WorkResult>>>

    suspend fun getMostCitedWork(
        fetchFromRemote: Boolean
    ):Flow<Resource<List<CitedResult>>>

    suspend fun getInstitutionsWithMostCitations(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<InstitutionResult>>>
}