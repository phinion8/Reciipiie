package com.priyanshu.reciipiie.data.remote

import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.domain.models.search.SearchRecipeList
import com.priyanshu.reciipiie.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {

    @GET("recipes/random")
    suspend fun getRandomRecipeList(
        @Query("apiKey") apiKey: String
    ): RandomRecipeList

    @GET("recipes/complexSearch?number=${Constants.PAGE_SIZE}")
    suspend fun getSearchRecipeList(
        @Query("apiKey") apiKey: String,
        @Query("offset") offset: Int
    ): SearchRecipeList

}