package com.example.core.database.localhome

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResearchTopicEntity(
    val citedByCount: Int,
    val createDate: String,
    val displayName: String,
    @PrimaryKey val id: String,
    val updatedDate: String,
)
