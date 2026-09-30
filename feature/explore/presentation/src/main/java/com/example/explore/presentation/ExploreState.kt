package com.example.explore.presentation

import com.example.explore.domain.model.WorkResult

data class ExploreState(
    val works: List<WorkResult> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
