package com.priyanshu.reciipiie.navigation

sealed class Screens(val route: String) {
    data object Splash: Screens(route = Routes.SPLASH_SCREEN)
    data object OnBoarding: Screens(route = Routes.ON_BOARDING_SCREEN)
    data object GoogleSignIn: Screens(route = Routes.GOOGLE_SIGN_IN_SCREEN)
    data object Home: Screens(route = Routes.HOME_SCREENS_ROUTE)
    data object Favorites: Screens(route = Routes.FAVOURITES_SCREEN)
    data object Profile: Screens(route = Routes.PROFILE_SCREEN)
    data object Search: Screens(route = Routes.SEARCH_SCREEN)
    data class RecipeDetails(val id: String? = null): Screens(route = Routes.RECIPE_DETAILS_SCREEN){
        fun buildRoute(): String {
            return if (id  != null){
                "${Routes.RECIPE_DETAILS_SCREEN}/$id"
            }else{
                "${Routes.RECIPE_DETAILS_SCREEN}/{id}"
            }
        }
    }
}