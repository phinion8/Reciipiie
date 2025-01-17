package com.priyanshu.reciipiie.ui.screens.search.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.reciipiie.domain.models.Recipe
import com.priyanshu.reciipiie.domain.models.search.Result
import com.priyanshu.reciipiie.domain.usecases.spoonacular_api_use_case.SpoonacularApiUseCase
import com.priyanshu.reciipiie.ui.screens.home.viewModel.SearchListState
import com.priyanshu.reciipiie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  SearchViewModel @Inject constructor(
    private val useCases: SpoonacularApiUseCase
): ViewModel() {

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private val _recipeInfoState: MutableStateFlow<Resource<Recipe>> = MutableStateFlow(Resource.Nothing())
    val recipeInfoState = _recipeInfoState.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _showSheet: MutableStateFlow<Pair<Boolean, Recipe?>> = MutableStateFlow(Pair(false, null))
    val showSheet = _showSheet.asStateFlow()

    val searchRecipeList = mutableStateListOf<Result>()
    var page by mutableIntStateOf(1)
    var canPaginate by mutableStateOf(false)
    var searchListState by mutableStateOf(SearchListState.IDLE)
    var error by mutableStateOf("")

    fun updateSheetState(value: Boolean){
        _showSheet.value = Pair(false, null)
    }

    fun loadSearchItemPaginated() {
        viewModelScope.launch {
            if (page == 1 || (page != 1 && canPaginate) && searchListState == SearchListState.IDLE)
                searchListState = if (page == 1) SearchListState.LOADING else SearchListState.PAGINATING
            useCases.getRecipeListFromSearchQuery(_searchQuery.value, page).collect{result->
                when (result) {
                    is Resource.Success -> {

                        if (result.data != null) {
                            canPaginate = result.data.results.size == 5

                            if (page == 1) {
                                searchRecipeList.clear()
                                searchRecipeList.addAll(result.data.results)
                            }else{
                                searchRecipeList.addAll(result.data.results)
                            }
                            searchListState = SearchListState.IDLE
                            if (canPaginate) {
                                page++
                            } else {
                                searchListState =
                                    if (page == 1) SearchListState.ERROR else SearchListState.PAGINATION_END
                            }

                        }

                    }

                    is Resource.Error -> {
                        searchListState = SearchListState.ERROR
                        error = result.message.toString()
                    }

                    else -> {}
                }

            }

        }
    }

    fun getRecipeInfo(id: Int){
        viewModelScope.launch {
            useCases.getRecipeInfo(id).collect{result->

                when(result){
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _isLoading.value = false
                        _showSheet.value = Pair(true, result.data)
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                        _showSheet.value = Pair(false, null)
                    }
                    else -> {}
                }

            }
        }

    }

    override fun onCleared() {
        page = 1
        searchListState = SearchListState.IDLE
        canPaginate = false
        super.onCleared()
    }


}