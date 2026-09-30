package com.example.home.data.remote

import com.example.home.data.remote.dto.CitedWorksDto
import com.example.home.data.remote.dto.InstitutionDto
import com.example.home.data.remote.dto.RecentWorksDto
import com.example.home.data.remote.dto.ResearchTopicDto
import retrofit2.http.GET

interface HomeApi {
    @GET("/topics")
    suspend fun getTopics(): ResearchTopicDto
    @GET("/works")
    suspend fun getRecentWorks(): RecentWorksDto
    @GET("/works")
    suspend fun getHighlyCitedWorks(): CitedWorksDto
    @GET("/institutions")
    suspend fun getInstitutions(): InstitutionDto
}