package com.priyanshu.reciipiie.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.priyanshu.reciipiie.domain.models.search.Result
import com.priyanshu.reciipiie.ui.theme.grey300

@Composable
fun SearchRecipeItem(result: Result, onItemClick: (id: String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Transparent)
            .padding(vertical = 8.dp)
            .border(width = 1.dp, color = grey300, shape = RoundedCornerShape(16.dp))
            .clickable {
                onItemClick(result.id.toString())
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = result.image,
            contentDescription = result.title,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            text = result.title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp,),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )


    }

}