package com.example.core.database.localhome

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InstitutionEntity(
    val citedByCount: Int,
    val displayName: String,
    @PrimaryKey val id: String,
)
