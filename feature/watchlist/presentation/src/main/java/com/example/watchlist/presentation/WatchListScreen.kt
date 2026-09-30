package com.example.watchlist.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListScreen(
    onClick: (String) -> Unit,
    viewModel: WatchListViewModel
) {
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {


            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(state.watchList){watchList->
                    WatchListCard(
                        watchList = watchList,
                        onClick = {
                            onClick(watchList.id)
                        },
                        onRemoveClick = { viewModel.removeFromWatchList(watchList) }
                    )

                }
            }
        }



}

