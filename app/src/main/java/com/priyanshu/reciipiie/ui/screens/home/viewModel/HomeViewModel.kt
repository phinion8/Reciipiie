package com.priyanshu.reciipiie.ui.screens.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.reciipiie.domain.models.RandomRecipeList
import com.priyanshu.reciipiie.domain.models.Recipe
import com.priyanshu.reciipiie.domain.usecases.OnBoardingUseCase
import com.priyanshu.reciipiie.domain.usecases.spoonacular_api_use_case.SpoonacularApiUseCase
import com.priyanshu.reciipiie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: OnBoardingUseCase,
    private val spoonacularApiUseCase: SpoonacularApiUseCase
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _userPhotoUrl = MutableStateFlow("")
    val userPhotoUrl = _userPhotoUrl.asStateFlow()

    private val _randomRecipeList: MutableStateFlow<RandomRecipeList> = MutableStateFlow(
        RandomRecipeList(emptyList())
    )
    val randomRecipeList = _randomRecipeList.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError: MutableStateFlow<String?> = MutableStateFlow(null)
    val isError = _isError.asStateFlow()

    init {
        viewModelScope.launch {
            useCases.readUserNameUseCase().collectLatest { value: String ->

                _userName.value = value

            }
            useCases.readUserPhotoUrlUseCase().collectLatest { value: String ->

                _userPhotoUrl.value = value

            }
        }
    }

    fun getRandomRecipeList() {
        viewModelScope.launch {
            spoonacularApiUseCase.getRandomRecipeList().collect { result ->

               when(result){
                   is Resource.Loading -> {
                       _isLoading.value = true
                   }
                   is Resource.Success -> {
                       if (result.data != null)
                       _randomRecipeList.value = result.data
                       _isLoading.value = false
                   }
                   is Resource.Error -> {
                       _isLoading.value = false
                       _isError.value = result.message.toString()
                   }
                   else -> {}
               }

            }
        }
    }

}