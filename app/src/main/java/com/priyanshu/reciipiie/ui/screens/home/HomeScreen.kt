package com.priyanshu.reciipiie.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.priyanshu.reciipiie.navigation.HomeNavGraph
import com.priyanshu.reciipiie.ui.components.BottomNavBar
import com.priyanshu.reciipiie.ui.components.models.bottomNavItems
import com.priyanshu.reciipiie.ui.screens.home.components.TopAppBar
import com.priyanshu.reciipiie.ui.screens.home.viewModel.HomeViewModel
import com.priyanshu.reciipiie.utils.isScrollingUp

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(

            content = { innerPadding ->

                HomeNavGraph(navController = navController)

            }, bottomBar = {
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
            })
    }


}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val userName: String by viewModel.userName.collectAsState()
    val userProfilePicUrl: String by viewModel.userPhotoUrl.collectAsState()
    val lazyColumnState = rememberLazyListState()
    val topBarVisibility = lazyColumnState.isScrollingUp()

    Column(modifier = Modifier.fillMaxSize()) {
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
    }
}