package com.priyanshu.reciipiie.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.navigation.HomeNavGraph
import com.priyanshu.reciipiie.navigation.Screens
import com.priyanshu.reciipiie.ui.components.BottomNavBar
import com.priyanshu.reciipiie.ui.components.ShowLottieAnimation
import com.priyanshu.reciipiie.ui.components.models.bottomNavItems
import com.priyanshu.reciipiie.ui.screens.home.components.HomeLoadingLayout
import com.priyanshu.reciipiie.ui.screens.home.components.PopularRecipeItem
import com.priyanshu.reciipiie.ui.screens.home.components.SearchBar
import com.priyanshu.reciipiie.ui.screens.home.components.SearchRecipeItem
import com.priyanshu.reciipiie.ui.screens.home.components.SearchRecipeItemLoading
import com.priyanshu.reciipiie.ui.screens.home.components.TopAppBar
import com.priyanshu.reciipiie.ui.screens.home.viewModel.HomeViewModel
import com.priyanshu.reciipiie.ui.screens.home.viewModel.SearchListState
import com.priyanshu.reciipiie.ui.theme.blue
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.utils.isScrollingUp
import com.priyanshu.reciipiie.utils.showToast
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    var bottomBarVisibility by remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            content = { innerPadding ->

                HomeNavGraph(navController = navController, innerPadding = innerPadding, showBottomBar = {
                    bottomBarVisibility = it
                })

            }, bottomBar = {
                if (bottomBarVisibility){
                    BottomNavBar(
                        modifier = Modifier.fillMaxWidth(),
                        bottomNavItems = bottomNavItems,
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route) {
                                this.launchSingleTop = true
                                this.restoreState = true
                            }
                        }
                    )
                }
            })
    }


}

@Composable
fun HomeScreenContent(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    innerPadding: PaddingValues
) {

    val userName: String by viewModel.userName.collectAsState()
    val userProfilePicUrl: String by viewModel.userPhotoUrl.collectAsState()
    val lazyColumnState = rememberLazyListState()
    val topBarVisibility = lazyColumnState.isScrollingUp()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnState.layoutInfo.totalItemsCount - 3)
        }
    }

    val searchRecipeList = viewModel.searchRecipeList

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && viewModel.searchListState == SearchListState.IDLE) {
            viewModel.loadSearchItemPaginated()
        }
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getRandomRecipeList()

    }


    LaunchedEffect(key1 = Unit) {
        if (isError != null){
            context.showToast(isError!!)
        }
    }

    val randomRecipeList by viewModel.randomRecipeList.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = innerPadding.calculateBottomPadding())
    ) {
        AnimatedVisibility(
            visible = topBarVisibility,
            exit = shrinkVertically(),
            enter = expandVertically()
        ) {
            TopAppBar(
                userName = userName,
                profilePicUrl = userProfilePicUrl
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(onSearchBarClick = {
            navController.navigate(Screens.Search.route)
        })
        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {
            HomeLoadingLayout()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = lazyColumnState
            ) {
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "IN THE SPOTLIGHT",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        PopularRecipesContent(randomRecipeList)

                        Text(
                            text = "RECIPES LIST",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        )

                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }

                items(searchRecipeList) {
                    SearchRecipeItem(result = it, onItemClick = { id ->
                        navController.navigate(Screens.RecipeDetails(id).buildRoute())
                    })
                }

                item {
                    when (viewModel.searchListState) {
                        SearchListState.LOADING -> {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                repeat(5) {
                                    SearchRecipeItemLoading()
                                }
                            }
                        }

                        SearchListState.PAGINATING -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                ShowLottieAnimation(
                                    rawRes = R.raw.loading_anim,
                                    modifier = Modifier.size(72.dp)
                                )
                            }

                        }

                        else -> {

                        }
                    }
                }

            }


        }


    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopularRecipesContent(
    randomRecipeList: RandomRecipeList
) {
    val pageCount = randomRecipeList.recipes.size
    val pagerState = rememberPagerState(
        pageCount = { pageCount },
    )
    HorizontalPager(
        state = pagerState
    ) { index ->
        PopularRecipeItem(recipe = randomRecipeList.recipes[index])
    }
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) blue else grey300
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(color, shape = CircleShape)
                    .size(10.dp)
            )
        }
    }
}




