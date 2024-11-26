package com.example.authtest.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val USER_TOKEN_KEY= stringPreferencesKey("user_token")
        private val USER_PASSWORD_KEY = stringPreferencesKey("user_password")


    }

    val getAccessToken: Flow<String> = context.dataStore.data
        .map{ preferences ->
        preferences[USER_TOKEN_KEY] ?: ""
    }

    val getPassword: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PASSWORD_KEY] ?: ""
        }


    suspend fun saveUserNameToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }
    suspend fun savePasswordToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = token
        }
    }

    suspend fun removeUserNameToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_TOKEN_KEY)
        }
    }

    suspend fun removePasswordToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_PASSWORD_KEY)
        }
    }


}