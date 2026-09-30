package com.example.home.data.remote.dto

import com.squareup.moshi.Json

data class Result(
    val id: String,
    @field:Json(name = "publication_date")
    val publicationDate: String = "",
    val title: String
)
