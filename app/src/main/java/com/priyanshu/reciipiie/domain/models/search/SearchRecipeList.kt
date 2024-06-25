package com.priyanshu.reciipiie.domain.models.search

data class SearchRecipeList(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)