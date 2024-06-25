package com.priyanshu.reciipiie.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.priyanshu.reciipiie.domain.repository.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

class PreferenceManagerImpl(context: Context) : PreferenceManager {
    companion object {
        const val ON_BOARDING_PREFERENCES_KEY = "on_boarding_preferences"
    }

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(ON_BOARDING_PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->

            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preferences ->

            val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
            onBoardingState

        }
    }

    override fun saveUserId(id: String) {
        TODO("Not yet implemented")
    }

    override fun readUserId(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun saveUsername(username: String) {
        TODO("Not yet implemented")
    }

    override fun readUsername(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun saveUserEmail(email: String) {
        TODO("Not yet implemented")
    }

    override fun readUserEmail(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun saveUserProfilePicUrl(url: String) {
        TODO("Not yet implemented")
    }

    override fun readUserProfilePicUr(): Flow<String> {
        TODO("Not yet implemented")
    }

}