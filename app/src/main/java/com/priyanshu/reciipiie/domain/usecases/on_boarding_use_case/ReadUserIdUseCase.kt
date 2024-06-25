package com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case

import com.priyanshu.reciipiie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadUserIdUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<String> {
        return repository.readUserId()
    }
}