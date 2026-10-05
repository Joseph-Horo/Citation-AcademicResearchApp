package com.example.core.database.localhome

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HomeDao {


    //RecentWorks
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentWorks(topics: List<RecentWorksEntity>)
    @Query("DELETE FROM recentworksentity")
    suspend fun clearRecentWorks()
    @Query("SELECT * FROM recentworksentity")
    suspend fun getRecentWorks(): List<RecentWorksEntity>

    //CitedWorks
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCitedWorks(cited: List<CitedWorksEntity>)
    @Query("DELETE FROM citedworksentity")
    suspend fun clearCitedWorks()
    @Query("SELECT * FROM citedworksentity")
    suspend fun getCitedWorks(): List<CitedWorksEntity>





}