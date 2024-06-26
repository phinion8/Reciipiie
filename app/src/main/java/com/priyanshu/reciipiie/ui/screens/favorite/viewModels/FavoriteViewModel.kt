package com.priyanshu.reciipiie.ui.screens.favorite.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.reciipiie.data.local.entities.RecipeItem
import com.priyanshu.reciipiie.domain.usecases.local_use_case.LocalRecipeUseCase
import com.priyanshu.reciipiie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val localRecipeUseCase: LocalRecipeUseCase
): ViewModel() {

    private val _favoriteRecipeList: MutableStateFlow<List<RecipeItem>> = MutableStateFlow(emptyList())
    val favoriteRecipeList = _favoriteRecipeList.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getFavoriteRecipeList(){
        viewModelScope.launch {
            localRecipeUseCase.getAllFavoriteRecipeList().collect{result->

                when(result){
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _isLoading.value = false
                        if (result.data != null) {
                            _favoriteRecipeList.value = result.data
                        }

                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                    }
                    else -> {}
                }

            }
        }
    }

    fun getLocalSearchRecipeList(){
        viewModelScope.launch {
            localRecipeUseCase.getLocalSearchRecipeList(_searchQuery.value).collect{result->

                when(result){
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _isLoading.value = false
                        if (result.data != null) {
                            Log.d("SOME_ISSUE", result.data.size.toString() + "for query ${_searchQuery.value}")
                            _favoriteRecipeList.value = result.data
                        }

                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                    }
                    else -> {}
                }

            }
        }
    }


}