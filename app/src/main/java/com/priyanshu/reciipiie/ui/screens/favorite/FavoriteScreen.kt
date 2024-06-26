package com.priyanshu.reciipiie.ui.screens.favorite

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.domain.models.search.Result
import com.priyanshu.reciipiie.ui.components.LoadingDialog
import com.priyanshu.reciipiie.ui.components.ShowLottieAnimation
import com.priyanshu.reciipiie.ui.screens.favorite.viewModels.FavoriteViewModel
import com.priyanshu.reciipiie.ui.screens.home.components.SearchRecipeItem
import com.priyanshu.reciipiie.ui.screens.home.components.SearchRecipeItemLoading
import com.priyanshu.reciipiie.ui.screens.home.viewModel.SearchListState
import com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.RecipeDetailBottomSheet
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.ui.theme.grey500
import com.priyanshu.reciipiie.ui.theme.lightGrey
import com.priyanshu.reciipiie.ui.theme.primaryColor
import com.priyanshu.reciipiie.utils.Resource

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getFavoriteRecipeList()
    }

    val favoriteRecipeList by viewModel.favoriteRecipeList.collectAsState()

    val searchQuery by viewModel.searchQuery
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current
    val isLoading by viewModel.isLoading.collectAsState()

    val lazyColumnState = rememberLazyListState()


    if (isLoading){
        LoadingDialog {}
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(grey500),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .weight(1f)
                    .padding(start = 16.dp, end = 8.dp),
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon),
                tint = lightGrey
            )
            Spacer(modifier = Modifier.width(12.dp))
            BasicTextField(
                onValueChange = { viewModel.updateSearchQuery(it) },
                value = searchQuery,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = primaryColor),
                modifier = Modifier
                    .weight(5f)
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()



                }),
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "Search Recipes",
                            style = MaterialTheme.typography.bodyLarge.copy(color = lightGrey)
                        )
                    }
                    innerTextField()
                },
                cursorBrush = SolidColor(primaryColor)
            )
            if (searchQuery.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .weight(1f)
                        .padding(start = 16.dp, end = 8.dp)
                        .clickable {
                            viewModel.updateSearchQuery("")
                        },
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = lightGrey
                )
            }
        }
        if (favoriteRecipeList.isEmpty() && isLoading.not()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "No Recipe Found", style = MaterialTheme.typography.bodyLarge.copy(color = grey300))
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = lazyColumnState
            ) {
                items(favoriteRecipeList) {item->
                    SearchRecipeItem(result = Result(item.id, image = item.imageUrl, "", item.title)) {

                    }
                }

                item {
                    if (isLoading) {
                        Column(
                            modifier = Modifier.padding(top = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            repeat(5) {
                                SearchRecipeItemLoading()
                            }
                        }
                    }
                }
            }
        }

    }

}