package com.example.core.database.localexplore

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExploreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorks(works: List<WorkEntity>)
    @Query("DELETE FROM workentity")
    suspend fun clearWorks()
    @Query("SELECT * FROM workentity WHERE title LIKE '%' || :query || '%' ")
    suspend fun searchWorks(query: String): List<WorkEntity>

}