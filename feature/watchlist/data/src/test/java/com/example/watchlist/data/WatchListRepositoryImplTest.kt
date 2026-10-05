package com.example.watchlist.data

import com.example.core.database.CitationDatabase
import com.example.core.database.localwatchlist.WatchListDao
import com.example.core.database.localwatchlist.WatchListEntity
import com.example.watchlist.data.repository.WatchListRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WatchListRepositoryImplTest {
    private lateinit var repository: WatchListRepositoryImpl
    private val db = mockk<CitationDatabase>()
    private val watchListDao = mockk<WatchListDao>()

    @Before
    fun setUp(){
        every {
            db.watchListDao
        }returns watchListDao
        repository = WatchListRepositoryImpl(db = db)
    }

    @Test
    fun `get watchlist returns saved data`() = runTest {
        val entities = listOf(
            WatchListEntity(
                title = "Radiation",
                citedByCount = 1,
                createdDate = "10/5/2026",
                id = "1"
            )
        )

        every {
            watchListDao.getWatchList()
        }returns flowOf(entities)

        val result = repository.getWatchList().first()

        assertThat(result).hasSize(1)
        assertThat(result[0].title).isEqualTo("Radiation")
        assertThat(result[0].citedByCount).isEqualTo(1)
        assertThat(result[0].createdDate).isEqualTo("10/5/2026")
        assertThat(result[0].id).isEqualTo("1")

        coVerify (exactly = 1){
            watchListDao.getWatchList()
        }

    }

}