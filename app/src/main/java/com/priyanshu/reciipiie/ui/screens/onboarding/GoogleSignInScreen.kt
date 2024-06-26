package com.priyanshu.reciipiie.ui.screens.onboarding

import android.content.IntentSender
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.ui.components.LoadingDialog
import com.priyanshu.reciipiie.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.ui.theme.primaryColor
import com.priyanshu.reciipiie.ui.theme.secondaryColor
import com.priyanshu.reciipiie.utils.Resource
import com.priyanshu.reciipiie.utils.showToast
import kotlinx.coroutines.flow.collect

@Composable
fun GoogleSignInScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }
    val activityResultLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->
            try {
                val oneTapClient = Identity.getSignInClient(context)
                val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken
                val authCredential = GoogleAuthProvider.getCredential(idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(authCredential)
                    .addOnCompleteListener { task ->
                        isLoading = false
                        val isNewUser = task.result.additionalUserInfo?.isNewUser
                        val userId = task.result.user!!.uid
                        val userName = task.result.user!!.displayName
                        val userEmail = task.result.user!!.email
                        val userPhotoUrl = task.result.user!!.photoUrl.toString()

                        if (userName != null && userEmail != null) {
                            viewModel.saveUserDetailsToPreferenceManager(
                                userId,
                                userName,
                                userEmail,
                                userPhotoUrl
                            )
                        }
                        navController.navigate("home"){
                            popUpTo("auth"){
                                inclusive = true
                            }
                        }

                    }.addOnFailureListener { error ->
                        isLoading = false
                        error.localizedMessage?.let { context.showToast(it) }
                    }

            } catch (e: Exception) {
                isLoading = false
                e.localizedMessage?.let { context.showToast(it) }
            }

        }

    LaunchedEffect(key1 = Unit) {
        viewModel.signWithGoogleState.collect { result ->

            when (result) {
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Success -> {
                    try {
                        val intentSenderRequest =
                            result.data?.pendingIntent?.intentSender?.let {
                                IntentSenderRequest.Builder(
                                    it
                                ).build()
                            }
                        activityResultLauncher.launch(intentSenderRequest)
                    } catch (e: IntentSender.SendIntentException) {
                        e.localizedMessage?.let { context.showToast(it) }

                    }
                }

                is Resource.Error -> {
                    isLoading = false
                }
                else->{}
            }

        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            LoadingDialog {}
        }
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App Icon"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.welcome_to_reciipie),
            style = MaterialTheme.typography.headlineLarge.copy(
                lineHeight = 64.sp,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.please_sign_in_to_continue_by_signing_up_you_agree_to_our_terms_and_conditions),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = grey300,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(shape = CircleShape)
                .background(primaryColor)
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .clickable {
                    viewModel.signUpWithGoogle(context)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = stringResource(R.string.google_icon)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.continue_with_google),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = secondaryColor,
                    fontWeight = FontWeight.Medium
                )
            )
        }

    }
}