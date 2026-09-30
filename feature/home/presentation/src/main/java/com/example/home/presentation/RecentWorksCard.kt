package com.example.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.domain.model.WorkResult

@Composable
fun ResearchTopicCard(
    onClick: () -> Unit,
    work: WorkResult
) {

    val colors = listOf(
        Color(0xFF87CEEB),
        Color(0xFFFFD166),
        Color(0xFFEC6FAE),
        Color(0xFFFFB86C),
        Color(0xFFC4B5FD),
    )
    val cardColor = colors.random()
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(250.dp)
            .padding(10.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = work.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                color = Color.White,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Cited By Count: ${work.publicationDate}",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
                )


        }
    }

}