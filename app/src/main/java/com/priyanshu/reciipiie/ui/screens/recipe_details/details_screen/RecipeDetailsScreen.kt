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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import com.priyanshu.reciipiie.domain.models.Ingredient
import com.priyanshu.reciipiie.domain.models.Step
import com.priyanshu.reciipiie.ui.components.CustomElevatedButton
import com.priyanshu.reciipiie.ui.components.ShowLottieAnimation
import com.priyanshu.reciipiie.ui.screens.home.components.SearchRecipeItemLoading
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.IngredientsAccordion
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.InstructionAccordion
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.RecipeInfoLayout
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.SimilarRecipeItem
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.viewModel.RecipeDetailBottomSheetViewModel
import com.priyanshu.reciipiie.ui.screens.recipe_details.details_screen.viewModel.RecipeDetailsViewModel
import com.priyanshu.reciipiie.ui.theme.blue
import com.priyanshu.reciipiie.ui.theme.darkGrey
import com.priyanshu.reciipiie.ui.theme.grey500
import com.priyanshu.reciipiie.ui.theme.lightGrey
import com.priyanshu.reciipiie.ui.theme.primaryColor
import com.priyanshu.reciipiie.ui.theme.white
import com.priyanshu.reciipiie.utils.Resource
import com.priyanshu.reciipiie.utils.showToast
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailsScreen(
    recipeId: String,
    navController: NavController,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {

    val recipe by viewModel.recipe.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.isItemFavorite(recipeId)
    }

    var isFavorite by remember {
        mutableStateOf(false)
    }
    isFavorite = viewModel.isItemFavorite.collectAsState().value
    val context = LocalContext.current
    val ingredientList: ArrayList<Ingredient> = ArrayList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getSimilarRecipeList(recipeId.toInt())
        viewModel.getRecipeInfo(recipeId.toInt())
    }

    val similarRecipeList by viewModel.similarRecipeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ShowLottieAnimation(rawRes = R.raw.loading_anim, modifier = Modifier.size(72.dp))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .size(35.dp).clickable {
                            navController.popBackStack()
                        },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back icon",
                    tint = primaryColor
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = recipe?.title ?: "",
                    style = MaterialTheme.typography.bodyLarge.copy(color = primaryColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

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
                    model = recipe?.image,
                    contentDescription = recipe?.title,
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
                                val recipeItem = recipe?.let {
                                    RecipeItem(
                                        recipeId = recipe?.id.toString(),
                                        imageUrl = it.image,
                                        title = recipe!!.title
                                    )
                                }
                                if (isFavorite) {
                                    isFavorite = false
                                    viewModel.deleteFavoriteRecipe(recipeId = recipe?.id.toString())
                                    context.showToast("Removed from Favorites list.")
                                } else {
                                    isFavorite = true
                                    if (recipeItem != null) {
                                        viewModel.addFavoriteRecipeItem(recipeItem)
                                        context.showToast("Added to Favorites list.")
                                    }

                                }
                            },
                        painter = painterResource(id = if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_like),
                        contentDescription = "Favorite icon",
                        tint = if (isFavorite) blue else Color.White.copy(alpha = 0.8f)
                    )
                }


            }



            Spacer(modifier = Modifier.height(12.dp))

            recipe?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.title,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = primaryColor,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RecipeInfoLayout(
                    modifier = Modifier.padding(vertical = 4.dp),
                    title = "Ready in",
                    value = "${recipe?.readyInMinutes} min"
                )
                RecipeInfoLayout(
                    modifier = Modifier.padding(vertical = 4.dp),
                    title = "Servings",
                    value = recipe?.servings.toString()
                )
                RecipeInfoLayout(
                    modifier = Modifier.padding(vertical = 4.dp),
                    title = "Price",
                    value = recipe?.pricePerServing.toString()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            recipe?.analyzedInstructions?.map { analyzedInstruction ->
                analyzedInstruction.steps.map { step: Step ->
                    step.ingredients.map { item ->
                        if (!ingredientList.contains(item)) {
                            ingredientList.add(item)
                        }
                    }
                }
            }

            IngredientsAccordion(ingredientList = ingredientList)

            recipe?.let { InstructionAccordion(instruction = it.instructions) }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, top = 8.dp),
                        text = "Similar Recipes",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = primaryColor,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                items(similarRecipeList) { item ->
                    SimilarRecipeItem(similarRecipeItem = item)
                }
            }


        }
    }
}