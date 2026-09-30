package com.example.home.domain.model

data class Institution(

    val results: List<InstitutionResult>
)

data class InstitutionResult(
    val citedByCount: Int,
    val displayName: String,
    val id: String
)
