package com.priyanshu.reciipiie.ui.screens.profile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.reciipiie.domain.usecases.OnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.SaveUserProfilePicUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: OnBoardingUseCase
): ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _userPhotoUrl = MutableStateFlow("")
    val userPhotoUrl = _userPhotoUrl.asStateFlow()

    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

    init {
        viewModelScope.launch {
            useCase.readUserNameUseCase().collectLatest { value: String ->

                _userName.value = value

            }
            useCase.readUserPhotoUrlUseCase().collectLatest { value: String ->

                _userPhotoUrl.value = value

            }
            useCase.readUserEmailUseCase().collectLatest { value: String ->

                _userEmail.value = value

            }
        }
    }



}