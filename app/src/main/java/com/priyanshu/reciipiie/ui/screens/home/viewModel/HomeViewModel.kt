package com.priyanshu.reciipiie.ui.screens.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.reciipiie.domain.usecases.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: OnBoardingUseCase
): ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _userPhotoUrl = MutableStateFlow("")
    val userPhotoUrl = _userPhotoUrl.asStateFlow()

    init {
        viewModelScope.launch {
            _userName.value = useCases.readUserNameUseCase().stateIn(this@launch).value
            _userPhotoUrl.value = useCases.readUserPhotoUrlUseCase().stateIn(this@launch).value
        }
    }

}