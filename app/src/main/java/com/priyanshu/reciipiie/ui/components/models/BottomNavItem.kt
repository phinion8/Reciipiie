package com.priyanshu.reciipiie.ui.components.models

import androidx.annotation.DrawableRes
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.navigation.Screens

data class BottomNavItem(
    @DrawableRes
    val icon: Int,
    val title: String,
    val route: String
)

val bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        icon = R.drawable.ic_home,
        title = "Home",
        route = Screens.Home.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_like,
        title = "Favorites",
        route = Screens.Favorites.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_profile,
        title = "Account",
        route = Screens.Profile.route
    )
)