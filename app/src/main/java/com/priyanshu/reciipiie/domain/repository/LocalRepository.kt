package com.priyanshu.reciipiie.domain.repository

import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getFavoriteRecipeList(): Flow<List<RecipeItem>>
    suspend fun insertFavoriteRecipe(recipeItem: RecipeItem)
    suspend fun deleteFavoriteRecipe(recipeItem: RecipeItem)

}