package com.priyanshu.reciipiie.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.ui.components.CustomElevatedButton
import com.priyanshu.reciipiie.ui.components.ShowLottieAnimation
import com.priyanshu.reciipiie.ui.theme.grey
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.ui.theme.lightGrey

@Composable
fun OnBoardingScreen(
    onGetStartedClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        ShowLottieAnimation(rawRes = R.raw.onboarding_anim, modifier = Modifier.size(380.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.don_t_know_what_to_cook),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.welcome_to_reciipiie_discover_delicious_recipes_and_unleash_your_inner_chef_let_s_start_cooking),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = grey300
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(42.dp))

            CustomElevatedButton(onClick = {
                onGetStartedClicked()
            }, text = stringResource(R.string.get_started))
        }

    }

}