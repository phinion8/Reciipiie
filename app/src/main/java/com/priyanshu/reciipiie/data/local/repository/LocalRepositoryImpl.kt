package com.priyanshu.reciipiie.data.local.repository

import com.priyanshu.reciipiie.data.local.dao.RecipeDao
import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import com.priyanshu.reciipiie.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
): LocalRepository {
    override fun getFavoriteRecipeList(): Flow<List<RecipeItem>> {
        return recipeDao.getAllFavoriteRecipe()
    }

    override suspend fun insertFavoriteRecipe(recipeItem: RecipeItem) {
        return recipeDao.addFavoriteRecipe(recipeItem)
    }

    override suspend fun deleteFavoriteRecipe(recipeItem: RecipeItem) {
        return recipeDao.deleteFavoriteRecipe(recipeItem)
    }
}