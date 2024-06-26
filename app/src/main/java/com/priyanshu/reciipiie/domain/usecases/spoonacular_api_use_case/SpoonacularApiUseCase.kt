package com.priyanshu.reciipiie.domain.usecases.spoonacular_api_use_case

import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.domain.models.Recipe
import com.priyanshu.reciipiie.domain.models.search.SearchRecipeList
import com.priyanshu.reciipiie.domain.repository.SpoonacularRepository
import com.priyanshu.reciipiie.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SpoonacularApiUseCase @Inject constructor(
    private val repository: SpoonacularRepository
) {

    fun getRandomRecipeList(): Flow<Resource<RandomRecipeList>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getRandomRecipeList()
            emit(Resource.Success(response))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }.catch {
        it.printStackTrace()
        emit(Resource.Error(it.localizedMessage))
    }

    fun getSearchRecipeList(offset: Int): Flow<Resource<SearchRecipeList>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getSearchRecipeList(offset)
            emit(Resource.Success(response))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }.catch {
        it.printStackTrace()
        emit(Resource.Error(it.localizedMessage))
    }

    fun getRecipeListFromSearchQuery(query: String, offset: Int): Flow<Resource<SearchRecipeList>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getRecipeListFromSearchQuery(query, offset)
            emit(Resource.Success(response))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }.catch {
        it.printStackTrace()
        emit(Resource.Error(it.localizedMessage))
    }

    fun getRecipeInfo(id: Int): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getRecipeInfo(id)
            emit(Resource.Success(response))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }.catch {
        it.printStackTrace()
        emit(Resource.Error(it.localizedMessage))
    }

}