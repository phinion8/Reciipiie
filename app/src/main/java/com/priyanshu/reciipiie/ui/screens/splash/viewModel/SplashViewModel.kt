package com.priyanshu.reciipiie.ui.screens.splash.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.reciipiie.domain.usecases.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: OnBoardingUseCase
) : ViewModel() {

    private val _onBoardingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onBoardingState = _onBoardingState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingState.value = useCases.readOnBoardingUseCase().stateIn(this@launch).value
        }
    }
}