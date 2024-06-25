package com.priyanshu.reciipiie.data.repository

import com.priyanshu.reciipiie.data.remote.SpoonacularApi
import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.domain.repository.SpoonacularRepository
import com.priyanshu.reciipiie.utils.Resource
import javax.inject.Inject

class SpoonacularRepositoryImpl @Inject constructor(
    private val spoonacularApi: SpoonacularApi
) : SpoonacularRepository {
    override suspend fun getRandomRecipeList(): RandomRecipeList {
        return spoonacularApi.getRandomRecipeList("c01a7de764ab4c04b0aeb091588618f8")
    }
}