package com.example.home.presentation

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (String) -> Unit,
    viewModel: HomeViewModel
) {
    val state = viewModel.state
    val lazyListState = rememberLazyListState()
    val snapBehaviour = rememberSnapFlingBehavior(lazyListState)

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = state.isRefreshing,
        onRefresh = {
            viewModel.onEvent(HomeEvent.Refresh)
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp)
        ) {
            item {
                Text("Recent Works",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp)
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                LazyRow(
                    state = lazyListState,
                    flingBehavior = snapBehaviour,
                    contentPadding = PaddingValues(horizontal = 10.dp)
                ) {
                    items(state.recentWorks){work->
                        RecentWorksCard(
                            work = work,
                            onClick = {
                                onClick(work.id)
                            }
                        )

                    }
                }
            }


            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                Text("Most Cited Works",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            items(state.citedWorks){cited->
              CitedWorkCard(
                  cited = cited,
                  onClick = {
                      onClick(cited.id)
                  }
              )

            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }






        }
    }

}