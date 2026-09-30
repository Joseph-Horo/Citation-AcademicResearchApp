package com.example.explore.presentation

sealed class ExploreEvent {
    object Refresh: ExploreEvent()
    data class OnSearchQueryChange(val query: String): ExploreEvent()
}