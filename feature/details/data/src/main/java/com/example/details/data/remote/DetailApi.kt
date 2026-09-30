package com.example.details.data.remote

import com.example.details.data.remote.dto.DetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET("/works/{id}")
    suspend fun getDetails(
        @Path("id") id: String
    ): DetailDto
}