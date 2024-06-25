package com.priyanshu.reciipiie.di

import android.content.Context
import com.priyanshu.reciipiie.data.repository.PreferenceManagerImpl
import com.priyanshu.reciipiie.data.repository.RepositoryImpl
import com.priyanshu.reciipiie.domain.repository.PreferenceManager
import com.priyanshu.reciipiie.domain.repository.Repository
import com.priyanshu.reciipiie.domain.usecases.OnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.read_onboarding.ReadOnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideRepository(preferenceManager: PreferenceManager): Repository {
        return RepositoryImpl(preferenceManager)
    }

    @Provides
    @Singleton
    fun provideOnBoardingUseCase(repository: Repository): OnBoardingUseCase{
        return OnBoardingUseCase(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
        )
    }

}