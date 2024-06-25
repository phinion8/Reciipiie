package com.priyanshu.reciipiie.domain.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}