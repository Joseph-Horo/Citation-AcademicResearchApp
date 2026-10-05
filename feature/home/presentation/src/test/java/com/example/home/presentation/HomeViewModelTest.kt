package com.example.home.presentation

import com.example.core.resource.Resource
import com.example.home.domain.repository.HomeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
private lateinit var viewModel: HomeViewModel
private val repository = mockk<HomeRepository>()

    @Before
    fun setup(){
        coEvery {
            repository.getRecentWorks(false)
        }returns flowOf(
            Resource.Success(emptyList())
        )
        coEvery {
            repository.getMostCitedWork(false)
        }returns flowOf(
            Resource.Success(emptyList())
        )

    }

    @Test
    fun `initialize fetches recent works and most cited works`() = runTest {
        viewModel = HomeViewModel(repository)
        advanceUntilIdle()
        coVerify(exactly = 1) {
            repository.getRecentWorks(false)
        }
        coVerify (exactly = 1) {
            repository.getMostCitedWork(false)
        }
    }

    @Test
    fun `Refresh fetches recent works and most cited works from remote`() = runTest {
        coEvery {
            repository.getRecentWorks(false)
        }returns flowOf()
        coEvery {
            repository.getMostCitedWork(false)
        }returns flowOf()

        coEvery {
            repository.getRecentWorks(true)
        }returns flowOf()
        coEvery {
            repository.getMostCitedWork(true)
        }returns flowOf()
        viewModel = HomeViewModel(repository)
        viewModel.onEvent(HomeEvent.Refresh)
        advanceUntilIdle()
        coVerify (exactly = 1){
            repository.getRecentWorks(true)
        }
        coVerify (exactly = 1) {
            repository.getMostCitedWork(true)
        }
    }


}