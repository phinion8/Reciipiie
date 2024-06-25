package com.priyanshu.reciipiie.domain.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveUserId(id: String)
    fun readUserId(): Flow<String>
    suspend fun saveUsername(username: String)
    fun readUsername(): Flow<String>
    suspend fun saveUserEmail(email: String)
    fun readUserEmail(): Flow<String>
    suspend fun saveUserProfilePicUrl(url: String)
    fun readUserProfilePicUrl(): Flow<String>
}