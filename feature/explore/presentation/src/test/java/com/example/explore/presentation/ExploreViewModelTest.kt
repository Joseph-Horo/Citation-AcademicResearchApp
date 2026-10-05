package com.example.explore.presentation

import com.example.explore.domain.repository.ExploreRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before

class ExploreViewModelTest {
    private lateinit var viewModel: ExploreViewModel
    private val repository = mockk<ExploreRepository>()

    @Before
    fun setup(){
        coEvery {
            repository.getWorks(false, query = String())
        }returns flowOf()
    }

}