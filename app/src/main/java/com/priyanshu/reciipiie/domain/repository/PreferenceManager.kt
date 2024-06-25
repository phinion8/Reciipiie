package com.priyanshu.reciipiie.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceManager {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>

    fun saveUserId(id: String)
    fun readUserId(): Flow<String>

    fun saveUsername(username: String)
    fun readUsername(): Flow<String>

    fun saveUserEmail(email: String)
    fun readUserEmail(): Flow<String>

    fun saveUserProfilePicUrl(url: String)
    fun readUserProfilePicUr(): Flow<String>

}