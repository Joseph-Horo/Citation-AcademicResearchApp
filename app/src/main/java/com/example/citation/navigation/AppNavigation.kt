package com.example.citation.navigation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.citation.CitationApp

import com.example.core.common.icons.BookMarkFill
import com.example.core.common.icons.BookMarkOut
import com.example.core.common.icons.HomeFill
import com.example.core.common.icons.HomeOut
import com.example.details.presentation.DetailViewModel
import com.example.details.presentation.DetailsScreen
import com.example.explore.presentation.ExploreScreen
import com.example.explore.presentation.ExploreViewModel
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.HomeTopBar
import com.example.home.presentation.HomeViewModel
import com.example.watchlist.presentation.WatchListScreen
import com.example.watchlist.presentation.WatchListTopBar
import com.example.watchlist.presentation.WatchListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val items = listOf(
        BottomBarItem(
            title = "Home",
            selectedIcon = HomeFill,
            unselectedIcon = HomeOut,
            route = "home"
        ),
        BottomBarItem(
            title = "Explore",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            route = "explore"
        ),

        BottomBarItem(
            title = "WatchList",
            selectedIcon = BookMarkFill,
            unselectedIcon = BookMarkOut,
            route = "watchlist"
        )
    )
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val coordinators = remember {
        object : AppCoordinators{
            override fun navigateToDetails(id: String) {
                navController.navigate("details/${Uri.encode(id)}")
            }

            override fun navigateBack() {
               navController.popBackStack()
            }

        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
             when(currentRoute){
                 "home" -> HomeTopBar(scrollBehaviour)
                 "watchlist" -> WatchListTopBar(scrollBehaviour)
                 else -> Unit
             }
        },
        bottomBar = {
            NavigationBar(
             containerColor = Color.White,
                tonalElevation = 0.dp
            )  {
             items.forEach { item ->
                 NavigationBarItem(
                     selected = currentRoute == item.route,
                     onClick = {
                         navController.navigate(item.route)
                     },
                     label = {
                         Text(item.title)
                     },
                     icon = {
                         Icon(imageVector = if (currentRoute == item.route){
                            item.selectedIcon
                         }else{
                             item.unselectedIcon
                         }, contentDescription = item.title)
                     }
                 )
             }

            }
        }
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home"){
                val application = LocalContext.current.applicationContext as CitationApp
                val viewModel: HomeViewModel = viewModel(
                    factory = application.appComponent.viewModelFactory()
                )
                HomeScreen(
                    onClick = {id->
                        coordinators.navigateToDetails(id)
                    },
                    viewModel = viewModel
                )
            }

            composable("explore"){
                val application = LocalContext.current.applicationContext as CitationApp
                val viewModel: ExploreViewModel = viewModel(
                    factory = application.appComponent.viewModelFactory()
                )
                ExploreScreen(
                    onClick = {id->
                        coordinators.navigateToDetails(id)
                    },
                    viewModel = viewModel
                )
            }
            composable("watchlist"){
                val application = LocalContext.current.applicationContext as CitationApp
                val viewModel: WatchListViewModel = viewModel(
                    factory = application.appComponent.viewModelFactory()
                )
                WatchListScreen(
                    onClick = {id->
                       coordinators.navigateToDetails(id)

                    },
                    viewModel = viewModel
                )
            }
            composable(route = "details/{id}", arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            )){backStackEntry->
                val id = backStackEntry.arguments?.getString("id") ?: return@composable
                val application = LocalContext.current.applicationContext as CitationApp
                val viewModel: DetailViewModel = viewModel(
                    factory = application.appComponent.viewModelFactory(),
                    extras = backStackEntry.defaultViewModelCreationExtras
                )
                DetailsScreen(
                    onBackClick = coordinators::navigateBack,
                    viewModel = viewModel
                )
            }
        }
    }

}