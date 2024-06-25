package com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case

import com.priyanshu.reciipiie.domain.repository.Repository

class SaveUserProfilePicUrlUseCase(private val repository: Repository) {
    suspend operator fun invoke(url: String){
        repository.saveUserProfilePicUrl(url = url)
    }
}