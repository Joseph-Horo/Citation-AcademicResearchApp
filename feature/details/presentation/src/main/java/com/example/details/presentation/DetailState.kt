package com.example.details.presentation

import com.example.details.domain.model.Detail

data class DetailState(
    val detail: Detail? = null,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val isInWatchList: Boolean = false,
)
