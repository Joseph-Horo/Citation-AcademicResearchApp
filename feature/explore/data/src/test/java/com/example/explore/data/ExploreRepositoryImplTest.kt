package com.example.explore.data

import com.example.core.database.CitationDatabase
import com.example.core.database.localexplore.ExploreDao
import com.example.core.database.localexplore.WorkEntity
import com.example.core.resource.Resource
import com.example.explore.data.remote.ExploreApi
import com.example.explore.data.repository.ExploreRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ExploreRepositoryImplTest {
private lateinit var repository: ExploreRepositoryImpl
private val api = mockk<ExploreApi>()
    private val db = mockk<CitationDatabase>()
    private val exploreDao = mockk<ExploreDao>()

    @Before
    fun setUp(){
        every {
            db.exploreDao
        }returns exploreDao

        repository = ExploreRepositoryImpl(db = db, api = api)
    }

    @Test
    fun `search works returns cached data when available`() = runTest {
        val entities = listOf(
            WorkEntity(
                title = "Radiation",
                createdDate = "10/5/2026",
                id = "1",
                citedByCount = 1

            )
        )

        coEvery {
            exploreDao.searchWorks(query = String())
        }returns entities

        val result = repository.getWorks(fetchFromRemote = false, query = String())
            .first{it is Resource.Success}

        assertThat(result).isInstanceOf(Resource.Success::class.java)
        coVerify(exactly = 1) {
            exploreDao.searchWorks(query = String())
        }
        coVerify (exactly = 0){
            api.getWorks()
        }
    }



}