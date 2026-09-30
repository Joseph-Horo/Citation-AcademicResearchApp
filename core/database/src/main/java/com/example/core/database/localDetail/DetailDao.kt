package com.example.core.database.localDetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: DetailEntity)
    @Query("SELECT * FROM detailentity WHERE id = :id")
    suspend fun getDetails(id: String): DetailEntity?
}