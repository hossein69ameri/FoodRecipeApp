package com.example.recipeapp.data.repository

import com.example.recipeapp.models.menu.MenuStoredModel
import com.example.recipeapp.utils.Constants
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class MenuRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object StoredKey {
        val selectMealTitle = stringPreferencesKey(Constants.MENU_MEAL_TITLE_KEY)
        val selectMealId = intPreferencesKey(Constants.MENU_MEAL_ID_KEY)
        val selectDietTitle = stringPreferencesKey(Constants.MENU_DIET_TITLE_KEY)
        val selectDietId = intPreferencesKey(Constants.MENU_DIET_ID_KEY)
    }

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(Constants.MENU_DATASTORE)

    suspend fun saveMenuData(meal: String, mealId: Int, diet: String, dietId: Int) {
        context.datastore.edit {
            it[StoredKey.selectMealTitle] = meal
            it[StoredKey.selectMealId] = mealId
            it[StoredKey.selectDietTitle] = diet
            it[StoredKey.selectDietId] = dietId
        }
    }

    val readMenuData: Flow<MenuStoredModel> = context.datastore.data
        .catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }
        .map {
            val selectMeal = it[StoredKey.selectMealTitle] ?: Constants.MAIN_COURSE
            val selectMealId = it[StoredKey.selectMealId] ?: 0
            val selectDiet = it[StoredKey.selectDietTitle] ?: Constants.GLUTEN_FREE
            val selectDietId = it[StoredKey.selectDietId] ?: 0
            MenuStoredModel(selectMeal, selectMealId, selectDiet, selectDietId)
        }
}