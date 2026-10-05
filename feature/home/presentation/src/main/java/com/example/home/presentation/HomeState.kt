package com.example.home.presentation

import com.example.home.domain.model.CitedResult

import com.example.home.domain.model.WorkResult

data class HomeState(
    val  recentWorks: List<WorkResult> = emptyList(),
    val  citedWorks: List<CitedResult> = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false
)
