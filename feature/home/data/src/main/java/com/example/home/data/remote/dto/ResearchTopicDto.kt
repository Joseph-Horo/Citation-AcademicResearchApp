package com.example.home.data.remote.dto

import com.squareup.moshi.Json

data class ResearchTopicDto(

    val results: List<TopicResult>
)

data class TopicResult(
    @field:Json(name = "cited_by_count")
    val citedByCount: Int = 0,
    @field:Json(name = "created_date")
    val createDate: String = "",
    @field:Json(name = "display_name")
    val displayName: String = "",
    val id: String,
    @field:Json(name = "updated_date")
    val updatedDate: String = "",
)