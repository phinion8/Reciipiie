package com.priyanshu.reciipiie.data.repository

import com.priyanshu.reciipiie.data.remote.SpoonacularApi
import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.domain.models.Recipe
import com.priyanshu.reciipiie.domain.models.search.SearchRecipeList
import com.priyanshu.reciipiie.domain.models.similar.SimilarRecipeList
import com.priyanshu.reciipiie.domain.repository.SpoonacularRepository
import javax.inject.Inject

class SpoonacularRepositoryImpl @Inject constructor(
    private val spoonacularApi: SpoonacularApi
) : SpoonacularRepository {
    override suspend fun getRandomRecipeList(): RandomRecipeList {
        return spoonacularApi.getRandomRecipeList("4131f388386e4e38abf5e765cf873d7f")
    }

    override suspend fun getSearchRecipeList(offset: Int): SearchRecipeList {
        return spoonacularApi.getSearchRecipeList("4131f388386e4e38abf5e765cf873d7f", offset = offset)
    }

    override suspend fun getRecipeListFromSearchQuery(
        query: String,
        offset: Int
    ): SearchRecipeList {
        return spoonacularApi.getRecipeListFromSearchQuery("4131f388386e4e38abf5e765cf873d7f", query, offset)
    }

    override suspend fun getRecipeInfo(id: Int): Recipe {
        return spoonacularApi.getRecipeInfo(id, "4131f388386e4e38abf5e765cf873d7f")
    }

    override suspend fun getSimilarRecipeList(id: Int): SimilarRecipeList {
        return spoonacularApi.getSimilarRecipeList(id, "4131f388386e4e38abf5e765cf873d7f")
    }

}