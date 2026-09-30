package com.example.details.data.repository

import com.example.core.database.CitationDatabase
import com.example.core.resource.Resource
import com.example.details.data.mapper.toDetail
import com.example.details.data.mapper.toDetailEntity
import com.example.details.data.mapper.toWatchListEntity
import com.example.details.data.remote.DetailApi
import com.example.details.domain.model.Detail
import com.example.details.domain.model.WatchList
import com.example.details.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepositoryImpl @Inject constructor(
    private val api: DetailApi,
    private val db: CitationDatabase
): DetailRepository {
    private val detailDao = db.detailDao
    private val watchListDao = db.watchListDao
    override suspend fun getDetails(
        fetchFromRemote: Boolean,
        id: String
    ): Flow<Resource<Detail>> {
       return flow {
           emit(Resource.Loading(false))
           val localDetail = detailDao.getDetails(id)
           localDetail?.let { detail ->
               emit(Resource.Success(data = detail.toDetail()))
           }
               val isDbEmpty = localDetail  == null
               val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
               if (shouldLoadFromCache){
                   emit(Resource.Loading(false))
                   return@flow
               }
               val remoteDetail = try {
                  api.getDetails(id).toDetail()

               }catch (e: IOException){
                   emit(Resource.Error("Couldn't load details"))
                   null
               }catch (e: HttpException){
                   emit(Resource.Error("Couldn't load details"))
                   null
               }
               remoteDetail?.let { details ->
                   detailDao.insertDetails(details.toDetailEntity())
                   emit(Resource.Success(data = detailDao.getDetails(details.id)?.toDetail()))
                   emit(Resource.Loading(false))

               }


       }
    }

    override suspend fun removeFromWatchList(watchList: WatchList) {
        watchListDao.removeWatchList(watchList.toWatchListEntity())
    }

    override suspend fun addToWatchList(watchList: WatchList) {
        watchListDao.insertWatchList(watchList.toWatchListEntity())
    }

    override fun isInWatchList(id: String): Flow<Boolean> {
        return watchListDao.isInWatchList(id)
    }
}