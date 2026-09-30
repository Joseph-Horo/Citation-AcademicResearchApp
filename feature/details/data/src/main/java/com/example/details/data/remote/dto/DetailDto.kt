package com.example.details.data.remote.dto

import com.squareup.moshi.Json


data class DetailDto(
    @field:Json(name = "cited_by_count")
    val citedByCount: Int = 0,
    @field:Json(name = "countries_distinct_count")
    val countriesDistinctCount: Int = 0,
    @field:Json(name = "created_date")
    val createdDate: String = "",
    val id: String,
    @field:Json(name = "publication_date")
    val publicationDate: String = "",
    @field:Json(name = "publication_year")
    val publicationYear: Int = 0,
    @field:Json(name = "referenced_works_count")
    val referencedWorksCount: Int = 0,
    val title: String,
    @field:Json(name = "updated_date")
    val updatedDate: String = ""

)