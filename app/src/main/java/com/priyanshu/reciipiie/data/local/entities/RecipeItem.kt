package com.priyanshu.reciipiie.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.priyanshu.reciipiie.utils.Constants

@Entity(tableName = Constants.RECIPE_TABLE)
data class RecipeItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: String,
    val title: String,
    val imageUrl: String
)