package com.priyanshu.reciipiie.di

import android.content.Context
import com.priyanshu.reciipiie.data.remote.SpoonacularApi
import com.priyanshu.reciipiie.data.repository.PreferenceManagerImpl
import com.priyanshu.reciipiie.data.repository.RepositoryImpl
import com.priyanshu.reciipiie.data.repository.SpoonacularRepositoryImpl
import com.priyanshu.reciipiie.domain.repository.PreferenceManager
import com.priyanshu.reciipiie.domain.repository.Repository
import com.priyanshu.reciipiie.domain.repository.SpoonacularRepository
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
import com.priyanshu.reciipiie.domain.usecases.spoonacular_api_use_case.SpoonacularApiUseCase
import com.priyanshu.reciipiie.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideOnBoardingUseCase(repository: Repository): OnBoardingUseCase {
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

    @Provides
    @Singleton
    fun provideSpoonacularApi(): SpoonacularApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(SpoonacularApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpoonacularRepository(spoonacularApi: SpoonacularApi): SpoonacularRepository {
        return SpoonacularRepositoryImpl(spoonacularApi)
    }

    @Provides
    @Singleton
    fun provideSpoonacularApiUseCase(repository: SpoonacularRepository): SpoonacularApiUseCase {
        return SpoonacularApiUseCase(repository)
    }

}