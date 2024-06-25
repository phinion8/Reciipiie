package com.priyanshu.reciipiie.domain.repository

import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.utils.Resource

interface SpoonacularRepository {
    suspend fun getRandomRecipeList(): RandomRecipeList
}