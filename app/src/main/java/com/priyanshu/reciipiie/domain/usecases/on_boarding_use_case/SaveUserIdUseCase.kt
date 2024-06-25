package com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case

import com.priyanshu.reciipiie.domain.repository.Repository

class SaveUserIdUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: String){
        repository.saveUserId(id = id)
    }
}