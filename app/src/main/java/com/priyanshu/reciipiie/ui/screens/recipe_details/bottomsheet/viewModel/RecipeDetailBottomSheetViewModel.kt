package com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import com.priyanshu.reciipiie.domain.models.similar.SimilarRecipeList
import com.priyanshu.reciipiie.domain.usecases.local_use_case.LocalRecipeUseCase
import com.priyanshu.reciipiie.domain.usecases.spoonacular_api_use_case.SpoonacularApiUseCase
import com.priyanshu.reciipiie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailBottomSheetViewModel @Inject constructor(
    private val useCase: SpoonacularApiUseCase,
    private val localRecipeUseCase: LocalRecipeUseCase
) : ViewModel() {

    private val _similarRecipeList: MutableStateFlow<SimilarRecipeList> = MutableStateFlow(
        SimilarRecipeList()
    )
    val similarRecipeList = _similarRecipeList.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isItemFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isItemFavorite = _isItemFavorite.asStateFlow()

    fun getSimilarRecipeList(id: Int) {
        viewModelScope.launch {
            useCase.getSimilarRecipeList(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        if (result.data != null)
                            _similarRecipeList.value = result.data
                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                    }

                    else -> {}
                }
            }
        }

    }

    fun addFavoriteRecipeItem(recipeItem: RecipeItem){
        viewModelScope.launch {
            localRecipeUseCase.addFavoriteRecipeItem(recipeItem)
        }
    }

    fun isItemFavorite(recipeId: String){
        viewModelScope.launch {
            localRecipeUseCase.getIsItemFavorite(recipeId = recipeId).collectLatest{result->
                when (result) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _isLoading.value = false
                        if (result.data != null)
                            _isItemFavorite.value = result.data
                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                    }

                    else -> {}
                }
            }
        }
    }

    fun deleteFavoriteRecipe(recipeId: String){
        viewModelScope.launch {
            localRecipeUseCase.deleteFavoriteRecipeItem(recipeId = recipeId)
        }
    }

}