package com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.priyanshu.reciipiie.domain.models.search.Result
import com.priyanshu.reciipiie.domain.models.similar.SimilarRecipeListItem
import com.priyanshu.reciipiie.ui.components.CustomElevatedButton
import com.priyanshu.reciipiie.ui.screens.home.components.SearchRecipeItem
import com.priyanshu.reciipiie.ui.screens.home.components.SearchRecipeItemLoading
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.viewModel.RecipeDetailBottomSheetViewModel
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSimilarBottomSheet(
    onDismiss: () -> Unit,
    id: String,
    viewModel: RecipeDetailBottomSheetViewModel = hiltViewModel()
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getSimilarRecipeList(id.toInt())
    }

    val similarRecipeList by viewModel.similarRecipeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item{
                    Text(modifier = Modifier.padding(bottom = 16.dp), text = "Similar Recipes", style = MaterialTheme.typography.bodyLarge.copy(color = primaryColor))
                }
                if (isLoading){
                    items(5){
                        SearchRecipeItemLoading()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                items(similarRecipeList) { item ->
                    SimilarRecipeItem(similarRecipeItem = item)
                }
            }

        }
    }
}

@Composable
fun SimilarRecipeItem(similarRecipeItem: SimilarRecipeListItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .border(width = 1.dp, color = grey300, shape = RoundedCornerShape(16.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = similarRecipeItem.title, style = MaterialTheme.typography.bodyLarge.copy(color = primaryColor))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "${similarRecipeItem.readyInMinutes} min | Servings: ${similarRecipeItem.servings}", style = MaterialTheme.typography.bodyMedium.copy(color = primaryColor, fontWeight = FontWeight.Bold))
    }
}