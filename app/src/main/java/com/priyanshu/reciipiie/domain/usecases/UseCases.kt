package com.priyanshu.reciipiie.domain.usecases


import com.priyanshu.reciipiie.domain.usecases.read_onboarding.ReadOnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.save_onboarding.SaveOnBoardingUseCase

data class OnBoardingUseCase(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
)