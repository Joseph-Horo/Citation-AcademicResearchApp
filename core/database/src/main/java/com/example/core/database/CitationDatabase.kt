package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.localDetail.DetailDao
import com.example.core.database.localDetail.DetailEntity
import com.example.core.database.localexplore.ExploreDao
import com.example.core.database.localexplore.WorkEntity
import com.example.core.database.localhome.CitedWorksEntity
import com.example.core.database.localhome.HomeDao
import com.example.core.database.localhome.InstitutionEntity
import com.example.core.database.localhome.RecentWorksEntity
import com.example.core.database.localhome.ResearchTopicEntity
import com.example.core.database.localwatchlist.WatchListDao
import com.example.core.database.localwatchlist.WatchListEntity

@Database(
    entities = [
        ResearchTopicEntity::class,
        RecentWorksEntity::class,
        CitedWorksEntity::class,
        InstitutionEntity::class,
        WorkEntity::class,
        DetailEntity::class,
        WatchListEntity::class
    ],
    version = 1
)
abstract class CitationDatabase: RoomDatabase() {

    abstract val homeDao: HomeDao
    abstract val exploreDao: ExploreDao
    abstract val detailDao: DetailDao
    abstract val watchListDao: WatchListDao
}