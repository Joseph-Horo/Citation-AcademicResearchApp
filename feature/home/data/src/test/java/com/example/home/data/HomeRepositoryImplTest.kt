package com.example.home.data
import com.example.core.database.CitationDatabase
import com.example.core.database.localhome.CitedWorksEntity
import com.example.core.database.localhome.HomeDao
import com.example.core.database.localhome.RecentWorksEntity
import com.example.core.resource.Resource
import com.example.home.data.remote.HomeApi
import com.example.home.data.repository.HomeRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeRepositoryImplTest {
    private lateinit var repository: HomeRepositoryImpl
    private val api = mockk<HomeApi>()
    private val db = mockk<CitationDatabase>()
    private val homeDao = mockk<HomeDao>()
@Before
    fun setUp(){
        every {
            db.homeDao
        }returns homeDao

        repository = HomeRepositoryImpl(
            db = db,
            api = api
        )
    }


    @Test
    fun `recent works returns cached data when available`() = runTest {
        val entities = listOf(
            RecentWorksEntity(
                id = "1",
                publicationDate = "10/5/2026",
                title = "Protein measurement"
            )
        )

        coEvery {
            homeDao.getRecentWorks()
        }returns entities

        val result = repository.getRecentWorks(fetchFromRemote = false)
            .first{it is Resource.Success}

        assertThat(result).isInstanceOf(Resource.Success::class.java)

        coVerify (exactly = 1){
           homeDao.getRecentWorks()
        }
        coVerify (exactly = 0){
            api.getRecentWorks()
        }
    }
    @Test
    fun `cited works returns cached data when available`() = runTest {
        val entities = listOf(
            CitedWorksEntity(
                id = "1",
                publicationDate = "10/5/2026",
                title = "Deep Residual Learning"
            )
        )
        coEvery {
            homeDao.getCitedWorks()
        }returns entities

        val result = repository.getMostCitedWork(fetchFromRemote = false)
            .first { it is Resource.Success }

        assertThat(result).isInstanceOf(Resource.Success::class.java)

        coVerify (exactly = 1){
            homeDao.getCitedWorks()
        }
        coVerify (exactly = 0){
            api.getHighlyCitedWorks()
        }
    }
}