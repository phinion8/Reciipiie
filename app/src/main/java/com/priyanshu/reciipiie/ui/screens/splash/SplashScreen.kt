package com.priyanshu.reciipiie.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.navigation.Screens
import com.priyanshu.reciipiie.ui.screens.splash.viewModel.SplashViewModel
import com.priyanshu.reciipiie.ui.theme.SPLASH_ICON_SIZE
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val onBoardingState = viewModel.onBoardingState.collectAsState()
    val currentUser = FirebaseAuth.getInstance().currentUser

    LaunchedEffect(key1 = Unit) {
        delay(1500)
        navController.popBackStack()
        if (currentUser != null){
            navController.navigate("home")
        }else{
            if (onBoardingState.value){
                navController.navigate(Screens.GoogleSignIn.route)
            }else{
                navController.navigate(Screens.OnBoarding.route){
                    popUpTo("onboarding"){
                        inclusive = true
                    }
                }
            }
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(SPLASH_ICON_SIZE),
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App logo"
        )
    }
}