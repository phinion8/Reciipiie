package com.priyanshu.reciipiie.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.navigation.Screens
import com.priyanshu.reciipiie.ui.components.CustomElevatedButton
import com.priyanshu.reciipiie.ui.screens.profile.viewModel.ProfileViewModel
import com.priyanshu.reciipiie.ui.theme.lightGrey

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val name by viewModel.userName.collectAsState()
    val email by viewModel.userEmail.collectAsState()
    val profilePhotoUrl by viewModel.userPhotoUrl.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp),
            model = profilePhotoUrl,
            placeholder = painterResource(id = R.drawable.sample_profile_img),
            error = painterResource(id = R.drawable.sample_profile_img),
            contentDescription = "Profile pic"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = name, style = MaterialTheme.typography.headlineLarge.copy(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = email, style = MaterialTheme.typography.bodyLarge.copy(color = lightGrey))
        Spacer(modifier = Modifier.height(24.dp))
        CustomElevatedButton(onClick = {
            viewModel.logOutUser()
            navController.navigate(Screens.OnBoarding.route)
        }, text = "Log Out")

    }
}