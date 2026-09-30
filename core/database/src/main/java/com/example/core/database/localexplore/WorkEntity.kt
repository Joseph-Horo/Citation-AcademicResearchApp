package com.example.core.database.localexplore

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkEntity(
    val citedByCount: Int,
    val createdDate: String,
    @PrimaryKey val id: String,
    val title: String
)
