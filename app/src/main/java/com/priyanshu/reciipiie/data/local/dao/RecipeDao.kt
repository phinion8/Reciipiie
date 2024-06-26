package com.priyanshu.reciipiie.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao{
    @Query("SELECT * FROM recipe_table ORDER BY id DESC")
    fun getAllFavoriteRecipe(): Flow<List<RecipeItem>>

    @Insert
    suspend fun addFavoriteRecipe(recipeItem: RecipeItem)

    @Delete
    suspend fun deleteFavoriteRecipe(recipeItem: RecipeItem)
}