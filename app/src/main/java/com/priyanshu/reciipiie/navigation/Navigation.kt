package com.priyanshu.reciipiie.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.priyanshu.reciipiie.ui.screens.home.HomeScreen
import com.priyanshu.reciipiie.ui.screens.home.HomeScreenContent
import com.priyanshu.reciipiie.ui.screens.onboarding.GoogleSignInScreen
import com.priyanshu.reciipiie.ui.screens.onboarding.OnBoardingScreen
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
fun HomeNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, route = "home", startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route){
            HomeScreenContent(innerPadding = innerPadding)
        }
        composable(route = Screens.Favorites.route){

        }
        composable(route = Screens.Profile.route){

        }
    }
}