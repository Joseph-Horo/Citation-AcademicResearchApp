package com.example.home.data.mapper

import com.example.core.database.localhome.CitedWorksEntity
import com.example.core.database.localhome.RecentWorksEntity
import com.example.home.data.remote.dto.CitedWorksDto

import com.example.home.data.remote.dto.RecentWorksDto
import com.example.home.data.remote.dto.Result

import com.example.home.domain.model.CitedResult

import com.example.home.domain.model.WorkResult




//CitedWorks
fun Result.toCitedResult(): CitedResult{
    return CitedResult(
        id = id,
        publicationDate = publicationDate,
        title = title
    )
}

fun CitedWorksDto.toCitedResult(): List<CitedResult>{
    return results.map { it.toCitedResult() }
}
fun CitedResult.toCitedWorksEntity(): CitedWorksEntity{
    return CitedWorksEntity(
        id = id,
        publicationDate = publicationDate,
        title = title
    )
}
fun CitedWorksEntity.toCitedResult(): CitedResult{
    return CitedResult(
        id = id,
        publicationDate = publicationDate,
        title = title
    )
}
//RecentWorks
fun Result.toWorkResult(): WorkResult{
    return WorkResult(
        id = id,
        publicationDate = publicationDate,
        title = title
    )
}
fun RecentWorksDto.toWorkResult(): List<WorkResult>{
    return results.map { it.toWorkResult() }
}
fun WorkResult.toRecentWorksEntity(): RecentWorksEntity{
    return RecentWorksEntity(
        id = id,
        publicationDate = publicationDate,
        title = title
    )
}
fun RecentWorksEntity.toWorkResult(): WorkResult{
    return WorkResult(
        id = id,
        publicationDate = publicationDate,
        title = title
    )
}


