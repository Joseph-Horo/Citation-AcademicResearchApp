package com.example.explore.data.repository

import com.example.core.database.CitationDatabase
import com.example.core.resource.Resource
import com.example.explore.data.mapper.toWorkEntity
import com.example.explore.data.mapper.toWorkResult
import com.example.explore.data.remote.ExploreApi
import com.example.explore.domain.model.WorkResult
import com.example.explore.domain.repository.ExploreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExploreRepositoryImpl @Inject constructor(
    private val db: CitationDatabase,
    private val api: ExploreApi
): ExploreRepository {
    private val exploreDao = db.exploreDao
    override suspend fun getWorks(fetchFromRemote: Boolean, query: String): Flow<Resource<List<WorkResult>>> {
       return flow {
           emit(Resource.Loading(false))
           val localWorks = exploreDao.searchWorks(query)
           emit(Resource.Success(data = localWorks.map { it.toWorkResult() }))
           val isDbEmpty = localWorks.isEmpty() && query.isBlank()
           val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
           if (shouldLoadFromCache){
               emit(Resource.Loading(false))
               return@flow
           }
           val remoteWorks = try {
            api.getWorks().toWorkResult()
           }catch (e: IOException){
               emit(Resource.Error("Couldn't load Works"))
               null
           }catch (e: HttpException){
               emit(Resource.Error("Couldn't load Works"))
               null
           }
           remoteWorks?.let { works->
               exploreDao.clearWorks()
               exploreDao.insertWorks(works.map { it.toWorkEntity() })
               emit(Resource.Success(data = exploreDao.searchWorks(query).map { it.toWorkResult() }))
               emit(Resource.Loading(false))

           }
       }
    }
}