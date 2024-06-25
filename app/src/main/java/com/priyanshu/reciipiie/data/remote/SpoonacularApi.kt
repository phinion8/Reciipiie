package com.priyanshu.reciipiie.data.remote

import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {

    @GET("recipes/random")
    suspend fun getRandomRecipeList(
        @Query("apiKey") apiKey: String
    ): RandomRecipeList

}