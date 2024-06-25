package com.priyanshu.reciipiie.navigation

sealed class Screens(val route: String) {
    data object Splash: Screens(route = Routes.SPLASH_SCREEN)
    data object OnBoardingScreen: Screens(route = Routes.ON_BOARDING_SCREEN)
    data object GoogleSignInScreen: Screens(route = Routes.GOOGLE_SIGN_IN_SCREEN)
}