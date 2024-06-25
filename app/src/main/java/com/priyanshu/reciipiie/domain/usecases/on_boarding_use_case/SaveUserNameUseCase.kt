package com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case

import com.priyanshu.reciipiie.domain.repository.Repository

class SaveUserNameUseCase(private val repository: Repository) {
    suspend operator fun invoke(username: String){
        repository.saveUsername(username = username)
    }
}