package com.priyanshu.reciipiie.domain.usecases


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

data class OnBoardingUseCase(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val readUserNameUseCase: ReadUserNameUseCase,
    val saveUserNameUseCase: SaveUserNameUseCase,
    val readUserEmailUseCase: ReadUserEmailUseCase,
    val saveUserEmailUseCase: SaveUserEmailUseCase,
    val readUserPhotoUrlUseCase: ReadUserProfilePicUseCase,
    val saveUserPhotoUrlUseCase: SaveUserProfilePicUrlUseCase,
    val readUserIdUseCase: ReadUserIdUseCase,
    val saveUserIdUseCase: SaveUserIdUseCase

)