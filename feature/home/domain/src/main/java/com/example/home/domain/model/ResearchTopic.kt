package com.example.home.domain.model
data class ResearchTopic(

    val results: List<Research>
)

data class Research(
    val citedByCount: Int,
    val createDate: String,
    val displayName: String,
    val id: String,
    val updatedDate: String
)
