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
        return spoonacularApi.getRandomRecipeList("e9af93f675904f36b937636ffc804abe")
    }

    override suspend fun getSearchRecipeList(offset: Int): SearchRecipeList {
        return spoonacularApi.getSearchRecipeList("e9af93f675904f36b937636ffc804abe", offset = offset)
    }

    override suspend fun getRecipeListFromSearchQuery(
        query: String,
        offset: Int
    ): SearchRecipeList {
        return spoonacularApi.getRecipeListFromSearchQuery("e9af93f675904f36b937636ffc804abe", query, offset)
    }

    override suspend fun getRecipeInfo(id: Int): Recipe {
        return spoonacularApi.getRecipeInfo(id, "e9af93f675904f36b937636ffc804abe")
    }

    override suspend fun getSimilarRecipeList(id: Int): SimilarRecipeList {
        return spoonacularApi.getSimilarRecipeList(id, "e9af93f675904f36b937636ffc804abe")
    }

}