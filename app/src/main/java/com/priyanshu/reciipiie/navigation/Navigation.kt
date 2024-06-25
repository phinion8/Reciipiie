package com.priyanshu.reciipiie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.priyanshu.reciipiie.ui.screens.onboarding.GoogleSignInScreen
import com.priyanshu.reciipiie.ui.screens.onboarding.OnBoardingScreen
import com.priyanshu.reciipiie.ui.screens.splash.SplashScreen

@Composable
fun SetUpNavigation(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screens.Splash.route){

        composable(route = Screens.Splash.route){
            SplashScreen(onSplashFinished = {
                navController.popBackStack()
                navController.navigate(Screens.OnBoardingScreen.route)
            })
        }

        composable(route = Screens.OnBoardingScreen.route){
            OnBoardingScreen(onGetStartedClicked = {
                navController.navigate(Screens.GoogleSignInScreen.route)
            })
        }

        composable(route = Screens.GoogleSignInScreen.route){
            GoogleSignInScreen()
        }
    }

}