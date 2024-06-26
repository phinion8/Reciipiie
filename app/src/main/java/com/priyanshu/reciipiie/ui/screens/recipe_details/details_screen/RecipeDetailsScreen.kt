package com.priyanshu.reciipiie.ui.screens.recipe_details.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import com.priyanshu.reciipiie.ui.components.CustomElevatedButton
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.RecipeInfoLayout
import com.priyanshu.reciipiie.ui.theme.blue
import com.priyanshu.reciipiie.ui.theme.darkGrey
import com.priyanshu.reciipiie.ui.theme.primaryColor
import com.priyanshu.reciipiie.utils.showToast

@Composable
fun RecipeDetailsScreen(
    recipeId: String,

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = recipe.image,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 12.dp, end = 12.dp)
                    .background(shape = CircleShape, color = darkGrey)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(if (isFavorite) 50.dp else 42.dp)
                        .padding(all = 8.dp)
                        .clickable {
                            val recipeItem = RecipeItem(
                                recipeId = recipe.id.toString(),
                                imageUrl = recipe.image,
                                title = recipe.title
                            )
                            if (isFavorite) {
                                isFavorite = false
                                viewModel.deleteFavoriteRecipe(recipeId = recipe.id.toString())
                                context.showToast("Removed from Favorites list.")
                            }else{
                                isFavorite = true
                                viewModel.addFavoriteRecipeItem(recipeItem)
                                context.showToast("Added to Favorites list.")
                            }
                        },
                    painter = painterResource(id = if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_like),
                    contentDescription = "Favorite icon",
                    tint = if (isFavorite) blue else Color.White.copy(alpha = 0.8f)
                )
            }


        }



        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = recipe.title,
            style = MaterialTheme.typography.headlineLarge.copy(
                color = primaryColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RecipeInfoLayout(
                modifier = Modifier.padding(vertical = 4.dp),
                title = "Ready in",
                value = "${recipe.readyInMinutes} min"
            )
            RecipeInfoLayout(
                modifier = Modifier.padding(vertical = 4.dp),
                title = "Servings",
                value = recipe.servings.toString()
            )
            RecipeInfoLayout(
                modifier = Modifier.padding(vertical = 4.dp),
                title = "Price",
                value = recipe.pricePerServing.toString()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        CustomElevatedButton(onClick = {
            showIngredientBottomSheet = true
        }, text = "Get Ingredients")


    }

}