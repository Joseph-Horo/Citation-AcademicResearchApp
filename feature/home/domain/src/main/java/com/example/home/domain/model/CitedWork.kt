package com.example.home.domain.model
data class CitedWork(

    val results: List<CitedResult>
)

data class CitedResult(
    val id: String,
    val publicationDate: String,
    val title: String,
)

