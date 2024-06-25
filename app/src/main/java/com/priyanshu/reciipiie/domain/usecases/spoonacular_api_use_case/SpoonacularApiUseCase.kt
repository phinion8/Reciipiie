package com.priyanshu.reciipiie.domain.usecases.spoonacular_api_use_case

import com.priyanshu.reciipiie.domain.models.RandomRecipeList
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
        emit(Resource.Error(it.localizedMessage))
    }

}