package com.priyanshu.reciipiie.domain.usecases.save_onboarding

import com.priyanshu.reciipiie.domain.repository.Repository


class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed = completed)
    }

}