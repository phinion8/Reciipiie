package com.priyanshu.reciipiie.ui.screens.profile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.priyanshu.reciipiie.domain.repository.PreferenceManager
import com.priyanshu.reciipiie.domain.usecases.OnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.SaveUserProfilePicUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: OnBoardingUseCase
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _userPhotoUrl = MutableStateFlow("")
    val userPhotoUrl = _userPhotoUrl.asStateFlow()

    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.readUserNameUseCase().collectLatest { value: String ->

                _userName.value = value

            }

        }
        viewModelScope.launch(Dispatchers.IO) {

            useCase.readUserEmailUseCase().collectLatest { value: String ->

                _userEmail.value = value

            }

        }

        viewModelScope.launch(Dispatchers.IO) {
            useCase.readUserPhotoUrlUseCase().collectLatest { value: String ->

                _userPhotoUrl.value = value

            }
        }
    }

    fun logOutUser() {
        auth.signOut()
        viewModelScope.launch {
            useCase.saveOnBoardingUseCase(false)
        }
    }


}