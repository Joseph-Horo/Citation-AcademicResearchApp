package com.example.explore.presentation

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
fun ExploreScreen(
    onClick: (String) -> Unit,
    viewModel: ExploreViewModel
) {
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        BasicTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(ExploreEvent.OnSearchQueryChange(it))

            },
            modifier = Modifier.fillMaxWidth()
                .size(50.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xFFF1F5F9)),
            textStyle = TextStyle(
                fontSize = 24.sp
            ),
            decorationBox = {innerTextField->
                Box(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (state.searchQuery.isBlank()){
                        Text("Search...")
                    }
                    innerTextField()
                }

            }

        )
        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            isRefreshing = state.isRefreshing,
            onRefresh = {
                viewModel.onEvent(ExploreEvent.Refresh)
            }
        ) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(state.works){work->
                    ExploreCard(
                        work = work,
                        onClick = {
                            onClick(work.id)
                        }
                    )

                }
            }
        }

    }

}