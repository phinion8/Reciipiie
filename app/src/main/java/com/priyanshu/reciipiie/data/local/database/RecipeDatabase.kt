package com.priyanshu.reciipiie.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.priyanshu.reciipiie.data.local.dao.RecipeDao
import com.priyanshu.reciipiie.data.local.entities.RecipeItem

@Database(entities = [RecipeItem::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}