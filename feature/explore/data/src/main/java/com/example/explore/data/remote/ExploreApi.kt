package com.example.explore.data.remote

import com.example.explore.data.remote.dto.WorksDto
import retrofit2.http.GET

interface ExploreApi {
    @GET("/works")
    suspend fun getWorks(): WorksDto
}