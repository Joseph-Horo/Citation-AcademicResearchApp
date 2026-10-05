package com.example.details.data

import com.example.core.database.CitationDatabase
import com.example.core.database.localDetail.DetailDao
import com.example.core.database.localDetail.DetailEntity
import com.example.core.database.localwatchlist.WatchListDao
import com.example.core.resource.Resource
import com.example.details.data.remote.DetailApi
import com.example.details.data.repository.DetailRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DetailRepositoryImplTest {
    private lateinit var repository: DetailRepositoryImpl

    private val api = mockk<DetailApi>()
    private val db = mockk<CitationDatabase>()
    private val detailDao = mockk<DetailDao>()
    private val watchListDao = mockk<WatchListDao>()

    @Before
    fun setUp(){
        every {
            db.detailDao
        }returns detailDao

        every {
            db.watchListDao
        }returns watchListDao

        repository = DetailRepositoryImpl(api = api, db = db)


    }


    @Test
    fun `get details returns cached data when available`() = runTest {
        val entity = DetailEntity(
                id = "1",
                publicationDate = "10/5/2026",
                title = "Deep Residual Learning",
                citedByCount = 1,
                countriesDistinctCount = 1,
                createdDate = "10/5/2026",
                referencedWorksCount = 1,
                updatedDate = "10/5/2026"
            )

        coEvery {
            detailDao.getDetails(id = String())
        }returns entity

        val result = repository.getDetails(fetchFromRemote = false, id = String())
            .first{it is Resource.Success}

        assertThat(result).isInstanceOf(Resource.Success::class.java)

        coVerify (exactly = 1){
            detailDao.getDetails(id = String())
        }
        coVerify(exactly = 0) {
            api.getDetails(id = String())
        }
    }
}