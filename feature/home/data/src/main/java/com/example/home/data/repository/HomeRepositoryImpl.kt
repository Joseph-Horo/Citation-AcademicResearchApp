package com.example.home.data.repository

import com.example.core.database.CitationDatabase
import com.example.core.resource.Resource
import com.example.home.data.mapper.toCitedResult
import com.example.home.data.mapper.toCitedWorksEntity

import com.example.home.data.mapper.toRecentWorksEntity

import com.example.home.data.mapper.toWorkResult
import com.example.home.data.remote.HomeApi
import com.example.home.domain.model.CitedResult

import com.example.home.domain.model.WorkResult
import com.example.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val db: CitationDatabase,
    private val api: HomeApi
): HomeRepository {
    private val homeDao = db.homeDao


    override suspend fun getRecentWorks(fetchFromRemote: Boolean): Flow<Resource<List<WorkResult>>> {
      return flow {
          emit(Resource.Loading(true))
          val localRecentWorks = homeDao.getRecentWorks()
          emit(Resource.Success(data = localRecentWorks.map { it.toWorkResult() }))

          val isDbEmpty = localRecentWorks.isEmpty()
          val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
          if (shouldLoadFromCache){
              emit(Resource.Loading(false))
              return@flow
          }
          val remoteRecentWorks = try {
              api.getRecentWorks().toWorkResult()
          }catch (e: IOException){
              emit(Resource.Error("Couldn't load Recent Works"))
              null
          }catch (e: HttpException){
              emit(Resource.Error("Couldn't load Recent Works"))
              null
          }
          remoteRecentWorks?.let { works->
              homeDao.clearRecentWorks()
              homeDao.insertRecentWorks(works.map { it.toRecentWorksEntity() })
              emit(Resource.Success(data = homeDao.getRecentWorks().map { it.toWorkResult() }))
              emit(Resource.Loading(false))
          }
      }
    }

    override suspend fun getMostCitedWork(fetchFromRemote: Boolean): Flow<Resource<List<CitedResult>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCitedWorks = homeDao.getCitedWorks()
            emit(Resource.Success(data = localCitedWorks.map { it.toCitedResult() }))

            val isDbEmpty = localCitedWorks.isEmpty()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteCitedWorks = try {
                api.getHighlyCitedWorks().toCitedResult()
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't load Most Cited Works"))
                null
            } catch (e: HttpException) {
                emit(Resource.Error("Couldn't load Most Cited Works"))
                null
            }
            remoteCitedWorks?.let { works ->
                homeDao.clearCitedWorks()
                homeDao.insertCitedWorks(works.map { it.toCitedWorksEntity() })
                emit(Resource.Success(data = homeDao.getCitedWorks().map { it.toCitedResult() }))
                emit(Resource.Loading(false))
            }
        }
    }}

