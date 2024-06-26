package com.priyanshu.reciipiie.di

import android.content.Context
import androidx.compose.ui.unit.Constraints
import androidx.room.Room
import com.priyanshu.reciipiie.data.local.dao.RecipeDao
import com.priyanshu.reciipiie.data.local.database.RecipeDatabase
import com.priyanshu.reciipiie.data.local.repository.LocalRepositoryImpl
import com.priyanshu.reciipiie.domain.repository.LocalRepository
import com.priyanshu.reciipiie.domain.usecases.local_use_case.LocalRecipeUseCase
import com.priyanshu.reciipiie.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideRecipeDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, RecipeDatabase::class.java, Constants.RECIPE_DATABASE_NAME)
        .build()

    @Provides
    @Singleton
    fun provideRecipeDao(database: RecipeDatabase) = database.recipeDao()

    @Provides
    @Singleton
    fun provideRecipeRepository(recipeDao: RecipeDao): LocalRepository =
        LocalRepositoryImpl(recipeDao)

    @Provides
    @Singleton
    fun provideLocalRecipeUseCase(localRepository: LocalRepository) = LocalRecipeUseCase(localRepository)

}