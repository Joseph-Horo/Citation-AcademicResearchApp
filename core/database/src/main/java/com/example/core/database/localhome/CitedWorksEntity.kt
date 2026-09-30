package com.example.core.database.localhome

import androidx.room.Entity

import androidx.room.PrimaryKey



@Entity
data class CitedWorksEntity(
    @PrimaryKey val id: String,
    val publicationDate: String,
    val title: String,

)
