package com.example.home.data.remote.dto

import com.squareup.moshi.Json

data class InstitutionDto(

    val results: List<ResultX>
)

data class ResultX(
    @field:Json(name = "cited_by_count")
    val citedByCount: Int = 0,
    @field:Json("display_name")
    val displayName: String = "",

    val id: String,
)