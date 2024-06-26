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
        return spoonacularApi.getRandomRecipeList("92339a9f1b2645aeaf7d33ebc838b2b9")
    }

    override suspend fun getSearchRecipeList(offset: Int): SearchRecipeList {
        return spoonacularApi.getSearchRecipeList("92339a9f1b2645aeaf7d33ebc838b2b9", offset = offset)
    }

    override suspend fun getRecipeListFromSearchQuery(
        query: String,
        offset: Int
    ): SearchRecipeList {
        return spoonacularApi.getRecipeListFromSearchQuery("92339a9f1b2645aeaf7d33ebc838b2b9", query, offset)
    }

    override suspend fun getRecipeInfo(id: Int): Recipe {
        return spoonacularApi.getRecipeInfo(id, "92339a9f1b2645aeaf7d33ebc838b2b9")
    }

    override suspend fun getSimilarRecipeList(id: Int): SimilarRecipeList {
        return spoonacularApi.getSimilarRecipeList(id, "92339a9f1b2645aeaf7d33ebc838b2b9")
    }

}