package com.example.recipeapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.recipeapp.data.source.RemoteDataSource
import com.example.recipeapp.model.BodyRegister
import com.example.recipeapp.model.RegisterStoreModel
import com.example.recipeapp.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class RegisterRepository @Inject constructor(
    private val remote: RemoteDataSource,
    @ApplicationContext val context: Context
) {
    //data store
    private object StoredKeys {
        val userName = stringPreferencesKey(Constants.REGISTER_USERNAME)
        val hash = stringPreferencesKey(Constants.REGISTER_HASH)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.REGISTER_USER_INFO)


    suspend fun saveRegisterData(username: String, hash: String) {
        context.dataStore.edit {
            it[StoredKeys.userName] = username
            it[StoredKeys.hash] = hash
        }
    }

    val readRegisterData: Flow<RegisterStoreModel> = context.dataStore.data
        .catch { e -> if (e is IOException) { emit(emptyPreferences()) } else { throw e } }
        .map {
            val username = it[StoredKeys.userName] ?: ""
            val hash = it[StoredKeys.hash] ?: ""
            RegisterStoreModel(username, hash)
        }

    //api
    suspend fun postRegister(apiKey: String, body: BodyRegister) = remote.postRegister(apiKey, body)
}