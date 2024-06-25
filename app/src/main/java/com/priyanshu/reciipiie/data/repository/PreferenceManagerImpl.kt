package com.priyanshu.reciipiie.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
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
        const val USER_ID_KEY = "user_id"
        const val USER_NAME_KEY = "user_name"
        const val USER_EMAIL_KEY = "user_email"
        const val USER_PROFILE_PIC_KEY = "user_profile_pic"
    }

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(ON_BOARDING_PREFERENCES_KEY)
        val userIdKey = stringPreferencesKey(USER_ID_KEY)
        val userNameKey = stringPreferencesKey(USER_NAME_KEY)
        val userEmailKey = stringPreferencesKey(USER_EMAIL_KEY)
        val userProfilePicKey = stringPreferencesKey(USER_PROFILE_PIC_KEY)
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

    override suspend fun saveUserId(id: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.userIdKey] = id
        }
    }

    override fun readUserId(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val userId = preferences[PreferencesKey.userIdKey] ?: ""
            userId
        }
    }

    override suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.userNameKey] = username
        }
    }

    override fun readUsername(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preferences ->
            val username = preferences[PreferencesKey.userNameKey] ?: ""
            Log.d("SOMEISSUE", "reading username as $username")
            username
        }
    }

    override suspend fun saveUserEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.userEmailKey] = email
        }
    }

    override fun readUserEmail(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val email = preferences[PreferencesKey.userEmailKey] ?: ""
            email
        }
    }

    override suspend fun saveUserProfilePicUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.userProfilePicKey] = url
        }
    }

    override fun readUserProfilePicUrl(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val url = preferences[PreferencesKey.userProfilePicKey] ?: ""
            url
        }
    }
}
