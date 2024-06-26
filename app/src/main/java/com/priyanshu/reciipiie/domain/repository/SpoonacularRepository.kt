package com.priyanshu.reciipiie.domain.repository

import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.domain.models.search.SearchRecipeList
import com.priyanshu.reciipiie.utils.Resource

interface SpoonacularRepository {
    suspend fun getRandomRecipeList(): RandomRecipeList
    suspend fun getSearchRecipeList(offset: Int): SearchRecipeList
    suspend fun getRecipeListFromSearchQuery(query: String, offset: Int): SearchRecipeList
}