package com.example.core.database.localDetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailEntity(
    val citedByCount: Int,
    val countriesDistinctCount: Int,
    val createdDate: String,
    @PrimaryKey val id: String,
    val publicationDate: String,
    val referencedWorksCount: Int,
    val title: String,
    val updatedDate: String
)
