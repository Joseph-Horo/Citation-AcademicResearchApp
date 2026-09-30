package com.example.home.presentation

import com.example.home.domain.model.CitedResult
import com.example.home.domain.model.InstitutionResult
import com.example.home.domain.model.Research
import com.example.home.domain.model.WorkResult

data class HomeState(
    val topics: List<Research> = emptyList(),
    val  recentWorks: List<WorkResult> = emptyList(),
    val  citedWorks: List<CitedResult> = emptyList(),
    val  institutions: List<InstitutionResult> = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false
)
