package com.priyanshu.reciipiie.domain.models.similar

data class SimilarRecipeListItem(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)