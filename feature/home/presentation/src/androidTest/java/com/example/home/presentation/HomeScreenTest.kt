package com.example.home.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.core.resource.Resource
import com.example.home.domain.model.CitedResult
import com.example.home.domain.model.WorkResult
import com.example.home.domain.repository.HomeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


class HomeScreenTest {
    @get: Rule
    val composeTestRule  = createComposeRule()

    private val repository = mockk<HomeRepository>()
    private val onClick = mockk<(String) -> Unit>(relaxed = true)


    @Test
    fun homeScreenDisplaysRecentWorksAndMostCitedWorks() = runTest {

        coEvery {
            repository.getRecentWorks(false)
        }returns flowOf(Resource.Success(emptyList()))

        coEvery {
            repository.getMostCitedWork(false)
        }returns flowOf(Resource.Success(emptyList()))
        val viewModel = HomeViewModel(repository)

        composeTestRule.setContent {
            HomeScreen(
                onClick = onClick,
                viewModel = viewModel
            )
        }
        composeTestRule.onNodeWithText("Recent Works")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Most Cited Works")
            .assertIsDisplayed()

    }

    @Test
    fun homeScreenDisplaysRecentWorksCard() = runTest {
        val works = listOf(
            WorkResult(
                id = "1",
                publicationDate = "10/5/2026",
                title = "Radiation"
            )

        )

        coEvery {
            repository.getRecentWorks(false)
        }returns flowOf(Resource.Success(works))
        coEvery {
            repository.getMostCitedWork(false)
        }returns flowOf(Resource.Success(emptyList()))

        val viewModel = HomeViewModel(repository)

        composeTestRule.setContent {
            HomeScreen(
                onClick = onClick,
                viewModel = viewModel
            )
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Radiation")
            .assertIsDisplayed()
    }
    @Test
    fun homeScreenDisplaysMostCitedWorksCard() = runTest {
        val cited = listOf(
            CitedResult(
                title = "Deep Residual Learning",
                id = "1",
                publicationDate = "10/5/2026"
            )
        )
        coEvery {
            repository.getMostCitedWork(false)

        }returns flowOf(Resource.Success(cited))

        coEvery {
            repository.getRecentWorks(false)

        }returns flowOf(Resource.Success(emptyList()))

        val viewModel = HomeViewModel(repository)

        composeTestRule.setContent {
            HomeScreen(
                onClick = onClick,
                viewModel = viewModel
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Deep Residual Learning")
            .assertIsDisplayed()
    }

}