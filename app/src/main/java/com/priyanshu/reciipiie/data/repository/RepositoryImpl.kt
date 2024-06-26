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

    override suspend fun saveUserId(id: String) {
        preferenceManager.saveUserId(id)
    }

    override fun readUserId(): Flow<String> {
        return preferenceManager.readUserId()
    }

    override suspend fun saveUsername(username: String) {
        preferenceManager.saveUsername(username)
    }

    override fun readUsername(): Flow<String> {
        return preferenceManager.readUsername()
    }

    override suspend fun saveUserEmail(email: String) {
         preferenceManager.saveUserEmail(email)
    }

    override fun readUserEmail(): Flow<String> {
        return preferenceManager.readUserEmail()
    }

    override suspend fun saveUserProfilePicUrl(url: String) {
         preferenceManager.saveUserProfilePicUrl(url)
    }

    override fun readUserProfilePicUrl(): Flow<String> {
        return preferenceManager.readUserProfilePicUrl()
    }

}