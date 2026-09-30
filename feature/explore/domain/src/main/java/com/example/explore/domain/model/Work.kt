package com.example.explore.domain.model

data class Work(
    val results: List<WorkResult>
)

data class WorkResult(
    val citedByCount: Int,
    val createdDate: String,
    val id: String,
    val title: String
)
