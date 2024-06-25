package com.priyanshu.reciipiie.data.repository

import com.priyanshu.reciipiie.domain.repository.PreferenceManager
import com.priyanshu.reciipiie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
): Repository {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        preferenceManager.saveOnBoardingState(completed = completed)
    }

    override fun readOnBoardingState(): Flow<Boolean> {
       return preferenceManager.readOnBoardingState()
    }

}