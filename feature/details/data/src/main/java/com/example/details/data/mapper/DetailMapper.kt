package com.example.details.data.mapper

import com.example.core.database.localDetail.DetailEntity
import com.example.core.database.localwatchlist.WatchListEntity
import com.example.details.data.remote.dto.DetailDto
import com.example.details.domain.model.Detail
import com.example.details.domain.model.WatchList

fun DetailDto.toDetail(): Detail{
    return Detail(
        citedByCount = citedByCount,
        id = id,
        title = title,
        createdDate = createdDate,
        countriesDistinctCount = countriesDistinctCount,
        publicationDate = publicationDate,
        referencedWorksCount = referencedWorksCount,
        updatedDate = updatedDate

    )
}
fun Detail.toDetailEntity(): DetailEntity{
    return DetailEntity(
        citedByCount = citedByCount,
        id = id,
        title = title,
        createdDate = createdDate,
        countriesDistinctCount = countriesDistinctCount,
        publicationDate = publicationDate,
        referencedWorksCount = referencedWorksCount,
        updatedDate = updatedDate
    )
}
fun DetailEntity.toDetail(): Detail{
    return Detail(
        citedByCount = citedByCount,
        id = id,
        title = title,
        createdDate = createdDate,
        countriesDistinctCount = countriesDistinctCount,
        publicationDate = publicationDate,
        referencedWorksCount = referencedWorksCount,
        updatedDate = updatedDate
    )
}
fun Detail.toWatchListEntity(): WatchListEntity{
    return WatchListEntity(
        id = id,
        title = title,
        citedByCount = citedByCount,
        createdDate = createdDate
    )
}
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
fun Detail.toWatchlist(): WatchList{
    return WatchList(
        id = id,
        title = title,
        citedByCount = citedByCount,
        createdDate = createdDate
    )
}
