package com.example.kaloricketabulkylite.ui.screens.search

import androidx.lifecycle.ViewModel
import com.example.kaloricketabulkylite.data.remote.response.search.SearchResponse
import com.example.kaloricketabulkylite.data.repository.search.SearchRepository
import com.example.kaloricketabulkylite.utils.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchRepository: SearchRepository
) : ViewModel() {

    private val _query = MutableStateFlow("Hobz")
    val query: StateFlow<String> = _query

    private val _searchResult = MutableStateFlow<Resource<SearchResponse>?>(null)
    val searchResult: StateFlow<Resource<SearchResponse>?> = _searchResult

    suspend fun search() {
        _searchResult.value = Resource.loading()

        searchRepository.getSearchPotraviny(_query.value).let {
            _searchResult.value = it
        }
    }

    fun setQuery(newValue: String) {
        _query.value = newValue
    }
}