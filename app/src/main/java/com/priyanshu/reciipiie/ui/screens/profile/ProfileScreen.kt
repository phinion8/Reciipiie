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
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.priyanshu.reciipiie.ui.screens.profile.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
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
                .size(150.dp)
                .clip(CircleShape),
            model = profilePhotoUrl,
            contentDescription = "Profile Photo",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = name, style = MaterialTheme.typography.headlineLarge.copy(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = email, style = MaterialTheme.typography.bodyLarge)

    }
}