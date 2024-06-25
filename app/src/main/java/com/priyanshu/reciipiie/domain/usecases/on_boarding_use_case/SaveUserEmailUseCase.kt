package com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case

import com.priyanshu.reciipiie.domain.repository.Repository

class SaveUserEmailUseCase(private val repository: Repository) {
    suspend operator fun invoke(email: String){
        repository.saveUserEmail(email = email)
    }
}