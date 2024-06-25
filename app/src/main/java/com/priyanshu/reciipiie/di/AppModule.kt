package com.priyanshu.reciipiie.di

import android.content.Context
import com.priyanshu.reciipiie.data.repository.PreferenceManagerImpl
import com.priyanshu.reciipiie.data.repository.RepositoryImpl
import com.priyanshu.reciipiie.domain.repository.PreferenceManager
import com.priyanshu.reciipiie.domain.repository.Repository
import com.priyanshu.reciipiie.domain.usecases.OnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.ReadOnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.ReadUserEmailUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.ReadUserIdUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.ReadUserNameUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.ReadUserProfilePicUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.SaveOnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.SaveUserEmailUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.SaveUserIdUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.SaveUserNameUseCase
import com.priyanshu.reciipiie.domain.usecases.on_boarding_use_case.SaveUserProfilePicUrlUseCase
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
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            saveUserIdUseCase = SaveUserIdUseCase(repository),
            readUserIdUseCase = ReadUserIdUseCase(repository),
            saveUserNameUseCase = SaveUserNameUseCase(repository),
            readUserNameUseCase = ReadUserNameUseCase(repository),
            saveUserEmailUseCase = SaveUserEmailUseCase(repository),
            readUserEmailUseCase = ReadUserEmailUseCase(repository),
            saveUserPhotoUrlUseCase = SaveUserProfilePicUrlUseCase(repository),
            readUserPhotoUrlUseCase = ReadUserProfilePicUseCase(repository)
        )
    }

}