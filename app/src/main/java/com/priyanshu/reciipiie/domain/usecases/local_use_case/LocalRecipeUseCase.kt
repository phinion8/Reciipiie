package com.priyanshu.reciipiie.domain.usecases.local_use_case

import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import com.priyanshu.reciipiie.domain.models.Recipe
import com.priyanshu.reciipiie.domain.repository.LocalRepository
import com.priyanshu.reciipiie.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRecipeUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    fun getAllFavoriteRecipeList(): Flow<Resource<List<RecipeItem>>> = flow {
        emit(Resource.Loading())
        try {
            val getAllFavoriteRecipeList = localRepository.getFavoriteRecipeList()
            getAllFavoriteRecipeList.collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    suspend fun addFavoriteRecipeItem(recipeItem: RecipeItem){
        localRepository.insertFavoriteRecipe(recipeItem)
    }

    suspend fun deleteFavoriteRecipeItem(recipeItem: RecipeItem){
        localRepository.deleteFavoriteRecipe(recipeItem)
    }

}