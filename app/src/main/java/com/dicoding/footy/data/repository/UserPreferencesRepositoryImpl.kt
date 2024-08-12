package com.dicoding.footy.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.dicoding.footy.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
): UserPreferencesRepository {

    override fun getFavoriteTeamId(): Flow<Int> {
        return userDataStorePreferences.data.map { preferences ->
            preferences[KEY] ?: 0
        }
    }

    override suspend fun saveFavoriteTeamId(favoriteTeamId: Int) {
        userDataStorePreferences.edit { preferences ->
            preferences[KEY] = favoriteTeamId
        }
    }

    override suspend fun deleteFavoriteTeamId() {
        userDataStorePreferences.edit { preferences ->
            preferences[KEY] = 0
        }
    }

    companion object {
        const val USER_PREFERENCES = "user_preferences"
        private val KEY = intPreferencesKey("favorite_team_id")
    }
}