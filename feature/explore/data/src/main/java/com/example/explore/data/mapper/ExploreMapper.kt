package com.example.explore.data.mapper

import com.example.core.database.localexplore.WorkEntity
import com.example.explore.data.remote.dto.Result
import com.example.explore.data.remote.dto.WorksDto
import com.example.explore.domain.model.WorkResult

fun Result.toWorkResult(): WorkResult{
    return WorkResult(
        citedByCount = citedByCount,
        createdDate = createdDate,
        id = id,
        title = title
    )
}
fun WorksDto.toWorkResult(): List<WorkResult>{
    return results.map { it.toWorkResult() }
}
fun WorkEntity.toWorkResult(): WorkResult{
    return WorkResult(
        citedByCount = citedByCount,
        createdDate = createdDate,
        id = id,
        title = title
    )
}
fun WorkResult.toWorkEntity(): WorkEntity{
    return WorkEntity(
        citedByCount = citedByCount,
        createdDate = createdDate,
        id = id,
        title = title
    )
}
