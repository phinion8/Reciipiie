package com.priyanshu.reciipiie.navigation

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.priyanshu.reciipiie.ui.screens.home.HomeScreen
import com.priyanshu.reciipiie.ui.screens.home.HomeScreenContent
import com.priyanshu.reciipiie.ui.screens.home.viewModel.HomeViewModel
import com.priyanshu.reciipiie.ui.screens.onboarding.GoogleSignInScreen
import com.priyanshu.reciipiie.ui.screens.onboarding.OnBoardingScreen
import com.priyanshu.reciipiie.ui.screens.search.SearchScreen
import com.priyanshu.reciipiie.ui.screens.splash.SplashScreen

@Composable
fun SetUpNavigation(
    navController: NavHostController
) {

    NavHost(navController = navController, route = "root", startDestination ="auth"){

        navigation(startDestination = Screens.Splash.route, route = "auth"){

            composable(route = Screens.Splash.route){
                SplashScreen(navController)
            }

            composable(route = Screens.OnBoarding.route){
                OnBoardingScreen(navController)
            }

            composable(route = Screens.GoogleSignIn.route){
                GoogleSignInScreen(navController)
            }

        }

        composable(route = "home"){
            HomeScreen()
        }

    }

}

@Composable
fun HomeNavGraph(navController: NavHostController, innerPadding: PaddingValues, showBottomBar: (Boolean) -> Unit) {
    NavHost(navController = navController, route = "home", startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route){
            showBottomBar(true)
            HomeScreenContent(innerPadding = innerPadding, navController = navController)
        }
        composable(route = Screens.Favorites.route){
            showBottomBar(true)
        }
        composable(route = Screens.Profile.route){
            showBottomBar(true)
        }
        composable(route = Screens.Search.route){
            showBottomBar(false)
            SearchScreen()
        }
    }
}