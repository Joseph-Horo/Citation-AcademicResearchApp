package com.example.explore.presentation

import com.example.core.resource.Resource
import com.example.explore.domain.repository.ExploreRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ExploreViewModelTest {
    private lateinit var viewModel: ExploreViewModel
    private val repository = mockk<ExploreRepository>()

    @Before
    fun setup(){
        coEvery {
            repository.getWorks(false, query = String())
        }returns flowOf(Resource.Success(emptyList()))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `initialization fetches get works`() = runTest {
        viewModel = ExploreViewModel(repository)
        advanceUntilIdle()
        coVerify(exactly = 1) {
            repository.getWorks(false, query = String())
        }
    }

}