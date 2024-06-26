package com.priyanshu.reciipiie.data.repository

import com.priyanshu.reciipiie.data.remote.SpoonacularApi
import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.domain.models.search.SearchRecipeList
import com.priyanshu.reciipiie.domain.repository.SpoonacularRepository
import javax.inject.Inject

class SpoonacularRepositoryImpl @Inject constructor(
    private val spoonacularApi: SpoonacularApi
) : SpoonacularRepository {
    override suspend fun getRandomRecipeList(): RandomRecipeList {
        return spoonacularApi.getRandomRecipeList("c01a7de764ab4c04b0aeb091588618f8")
    }

    override suspend fun getSearchRecipeList(offset: Int): SearchRecipeList {
        return spoonacularApi.getSearchRecipeList("c01a7de764ab4c04b0aeb091588618f8", offset = offset)
    }

    override suspend fun getRecipeListFromSearchQuery(
        query: String,
        offset: Int
    ): SearchRecipeList {
        return spoonacularApi.getRecipeListFromSearchQuery("c01a7de764ab4c04b0aeb091588618f8", query, offset)
    }
}