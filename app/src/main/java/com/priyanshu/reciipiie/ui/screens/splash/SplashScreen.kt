package com.priyanshu.reciipiie.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.ui.theme.SPLASH_ICON_SIZE
import com.priyanshu.reciipiie.ui.theme.darkBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        delay(1500)
        onSplashFinished()
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