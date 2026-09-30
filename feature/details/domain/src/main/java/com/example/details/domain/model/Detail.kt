package com.example.details.domain.model

data class Detail(
    val citedByCount: Int,
    val countriesDistinctCount: Int,
    val createdDate: String,
    val id: String,
    val publicationDate: String,
    val referencedWorksCount: Int,
    val title: String,
    val updatedDate: String
)
