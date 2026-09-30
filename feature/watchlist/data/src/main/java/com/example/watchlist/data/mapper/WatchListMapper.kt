package com.example.watchlist.data.mapper

import com.example.core.database.localwatchlist.WatchListEntity
import com.example.watchlist.domain.model.WatchList

fun WatchListEntity.toWatchList(): WatchList{
    return WatchList(
        id = id,
        title = title,
        citedByCount = citedByCount,
        createdDate = createdDate
    )
}
fun WatchList.toWatchListEntity(): WatchListEntity{
    return WatchListEntity(
        id = id,
        title = title,
        citedByCount = citedByCount,
        createdDate = createdDate
    )
}