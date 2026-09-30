package com.example.core.database.localwatchlist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchList(watchList: WatchListEntity)
    @Delete
    suspend fun removeWatchList(watchList: WatchListEntity)
    @Query("SELECT * FROM watchlistentity")
    fun getWatchList(): Flow<List<WatchListEntity>>
    @Query("SELECT EXISTS(SELECT 1 FROM watchlistentity WHERE id = :id)")
    fun isInWatchList(id: String): Flow<Boolean>
}