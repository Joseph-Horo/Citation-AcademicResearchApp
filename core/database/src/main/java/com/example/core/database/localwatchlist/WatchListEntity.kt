package com.example.core.database.localwatchlist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchListEntity(
    val citedByCount: Int,
    @PrimaryKey val id: String,
    val title: String,
    val createdDate: String
)