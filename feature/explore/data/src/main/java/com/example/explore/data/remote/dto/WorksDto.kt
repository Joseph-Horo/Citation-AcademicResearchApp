package com.example.explore.data.remote.dto

import com.squareup.moshi.Json

data class WorksDto(

    val results: List<Result>
)

data class Result(
    @field:Json(name = "cited_by_count")
    val citedByCount: Int = 0,
    @field:Json(name = "created_date" )
    val createdDate: String = "",
    val id: String,
    val title: String,


)