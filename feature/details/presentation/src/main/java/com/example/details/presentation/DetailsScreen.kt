package com.example.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.example.core.common.icons.ArrowBack

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.common.icons.StarFill
import com.example.core.common.icons.StarOut

@Composable
fun DetailsScreen(
    onBackClick: ()-> Unit,
    viewModel: DetailViewModel
) {
    val state = viewModel.state
    state.detail?.let { detail ->

        Column(modifier = Modifier.fillMaxSize()
            .padding(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(imageVector = ArrowBack , contentDescription = "Back" )
                }
                IconButton(
                    onClick = {
                        if (state.isInWatchList){
                            viewModel.removeFromWatchList()
                        }else{
                            viewModel.addToWatchList()
                        }
                    }
                ) {
                    Icon(imageVector = if (state.isInWatchList){
                        StarFill
                    }else{
                        StarOut
                    }, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(50.dp))
                }

            }


            Text(text = detail.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                maxLines = 3)


            HorizontalDivider(modifier = Modifier.padding(10.dp))

            Text(text = "Cited By Count: ${detail.citedByCount}", fontSize = 18.sp)
            Text(text = "Date Created: ${detail.createdDate}", fontSize = 18.sp)
            Text(text = "Date of Publication: ${detail.publicationDate}", fontSize = 18.sp)
            Text(text = "Updated Date: ${detail.updatedDate}", fontSize = 18.sp)
            Text(text = "Referenced Works Count: ${detail.referencedWorksCount}", fontSize = 18.sp)
            Text(text = "Countries Distinct Count: ${detail.countriesDistinctCount}", fontSize = 18.sp)
        }

    }
}