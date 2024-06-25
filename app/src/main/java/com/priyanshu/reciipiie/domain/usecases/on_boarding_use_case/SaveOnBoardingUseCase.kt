package com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case

import com.priyanshu.reciipiie.domain.repository.Repository


class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed = completed)
    }

}