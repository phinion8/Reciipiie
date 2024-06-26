package com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import com.priyanshu.reciipiie.domain.models.Ingredient
import com.priyanshu.reciipiie.domain.models.Recipe
import com.priyanshu.reciipiie.domain.models.Step
import com.priyanshu.reciipiie.ui.components.CustomElevatedButton
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.viewModel.RecipeDetailBottomSheetViewModel
import com.priyanshu.reciipiie.ui.theme.blue
import com.priyanshu.reciipiie.ui.theme.darkGrey
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.ui.theme.grey500
import com.priyanshu.reciipiie.ui.theme.lightGrey
import com.priyanshu.reciipiie.ui.theme.primaryColor
import com.priyanshu.reciipiie.ui.theme.secondaryColor
import com.priyanshu.reciipiie.utils.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailBottomSheet(
    onDismiss: () -> Unit,
    recipe: Recipe,
    viewModel: RecipeDetailBottomSheetViewModel = hiltViewModel()
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var showIngredientBottomSheet by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    if (showIngredientBottomSheet) {
        IngredientsBottomSheet(
            onDismiss = {
                showIngredientBottomSheet = false
            }, analyzedInstructions = recipe.analyzedInstructions, recipe.instructions,
            recipe.id.toString()
        )
    }

    LaunchedEffect(Unit) {
        viewModel.isItemFavorite(recipeId = recipe.id.toString())
    }

    var isFavorite by remember {
        mutableStateOf(false)
    }

    isFavorite = viewModel.isItemFavorite.collectAsState().value

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
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
}

@Composable
fun RecipeInfoLayout(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {

    Column(
        modifier = modifier
            .background(color = Color.Transparent)
            .border(width = 1.dp, color = grey300, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 10.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = lightGrey,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = primaryColor,
                fontSize = 14.sp
            )
        )
    }

}

@Composable
fun IngredientsAccordion(modifier: Modifier = Modifier, ingredientList: List<Ingredient>) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier.padding(vertical = 8.dp)) {
        AccordionHeader(title = "Ingredients", isExpanded = expanded) {
            expanded = !expanded
        }
        AnimatedVisibility(visible = expanded) {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, grey300),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    items(ingredientList) { item ->
                        IngredientItem(ingredient = item)
                    }
                }
            }
        }
    }
}

@Composable
fun InstructionAccordion(modifier: Modifier = Modifier, instruction: String) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier.padding(vertical = 8.dp)) {
        AccordionHeader(title = "Instructions", isExpanded = expanded) {
            expanded = !expanded
        }
        AnimatedVisibility(visible = expanded) {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, grey300),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp),
                    text = instruction,
                    style = MaterialTheme.typography.bodyMedium.copy(color = primaryColor)
                )
            }
        }
    }
}

@Composable
private fun AccordionHeader(
    title: String,
    isExpanded: Boolean = false,
    onTapped: () -> Unit = {}
) {
    val degrees = if (isExpanded) 180f else 0f

    Surface(
        color = secondaryColor,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, grey300)
    ) {
        Row(
            modifier = Modifier
                .clickable { onTapped() }
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                color = primaryColor
            )
            Surface(shape = CircleShape, color = grey500) {
                Icon(
                    Icons.Outlined.ArrowDropDown,
                    contentDescription = "arrow-down",
                    modifier = Modifier.rotate(degrees),
                    tint = blue
                )
            }
        }
    }
}

@Composable
fun IngredientItem(modifier: Modifier = Modifier, ingredient: Ingredient) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            model = ingredient.image,
            contentDescription = "Ingredient Image"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.width(64.dp),
            text = ingredient.name,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = primaryColor,
                textAlign = TextAlign.Center
            )
        )
    }

}