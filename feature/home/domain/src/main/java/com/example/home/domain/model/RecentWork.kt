package com.example.home.domain.model

data class RecentWork(
    val results: List<WorkResult>
)
data class WorkResult(
    val id: String,
    val publicationDate: String,
    val title: String,
)



