package com.example.home.data.mapper

import com.example.core.database.localhome.CitedWorksEntity
import com.example.core.database.localhome.InstitutionEntity
import com.example.core.database.localhome.RecentWorksEntity
import com.example.core.database.localhome.ResearchTopicEntity
import com.example.home.data.remote.dto.CitedWorksDto
import com.example.home.data.remote.dto.InstitutionDto
import com.example.home.data.remote.dto.RecentWorksDto
import com.example.home.data.remote.dto.ResearchTopicDto
import com.example.home.data.remote.dto.Result
import com.example.home.data.remote.dto.ResultX
import com.example.home.data.remote.dto.TopicResult
import com.example.home.domain.model.CitedResult
import com.example.home.domain.model.InstitutionResult
import com.example.home.domain.model.Research
import com.example.home.domain.model.WorkResult

//ResearchTopic
fun TopicResult.toResearch(): Research{
    return Research(
        citedByCount = citedByCount,
        createDate = createDate,
        displayName = displayName,
        id = id,
        updatedDate = updatedDate
    )
}
fun ResearchTopicDto.toResearch(): List<Research>{
    return results.map { it.toResearch() }
}
fun Research.toResearchTopicEntity(): ResearchTopicEntity{
    return ResearchTopicEntity(
        citedByCount = citedByCount,
        createDate = createDate,
        displayName = displayName,
        id = id,
        updatedDate = updatedDate
    )
}
fun ResearchTopicEntity.toResearch(): Research{
    return Research(
        citedByCount = citedByCount,
        createDate = createDate,
        displayName = displayName,
         id = id,
        updatedDate = updatedDate
    )
}
//Institution

fun ResultX.toInstitutionResult(): InstitutionResult{
    return InstitutionResult(
        citedByCount = citedByCount,
        displayName = displayName,
        id = id
    )
}
fun InstitutionDto.toInstitutionResult(): List<InstitutionResult>{
    return results.map { it.toInstitutionResult() }
}
fun InstitutionEntity.toInstitutionResult(): InstitutionResult{
    return InstitutionResult(
        citedByCount = citedByCount,
        displayName = displayName,
        id = id
    )
}
fun InstitutionResult.toInstitutionEntity(): InstitutionEntity{
    return InstitutionEntity(
        citedByCount = citedByCount,
        displayName = displayName,
        id = id
    )
}

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


