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

    @Query("SELECT * FROM recipe_table WHERE title LIKE '%' || :searchQuery || '%'")
    fun searchFavoriteList(searchQuery: String): Flow<List<RecipeItem>>

    @Query("SELECT EXISTS (SELECT 1 FROM recipe_table WHERE recipeId = :recipeId)")
    fun isItemFavorite(recipeId: String): Flow<Boolean>

    @Insert
    suspend fun addFavoriteRecipe(recipeItem: RecipeItem)

    @Query("DELETE FROM recipe_table WHERE recipeId = :recipeId")
    suspend fun deleteFavoriteRecipe(recipeId: String)
}