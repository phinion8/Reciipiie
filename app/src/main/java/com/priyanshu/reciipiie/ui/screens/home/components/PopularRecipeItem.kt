package com.priyanshu.reciipiie.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.priyanshu.reciipiie.domain.models.Recipe

@Composable
fun PopularRecipeItem(
    recipe: Recipe
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp)),

            ) {

            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = recipe.image,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                alpha = 0.7f
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 16.dp, start = 12.dp, end = 12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(text = recipe.title, style = MaterialTheme.typography.headlineLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium, lineHeight = 24.sp))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${recipe.readyInMinutes} min | Servings: ${recipe.servings} | ${if (recipe.vegetarian) "Vegetarian" else "Non Vegetarian"}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp)
                )

            }

        }
    }

}