package com.example.kaloricketabulkylite.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaloricketabulkylite.data.remote.response.search.SearchResponse
import com.example.kaloricketabulkylite.data.repository.search.SearchRepository
import com.example.kaloricketabulkylite.utils.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchRepository: SearchRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _searchResult = MutableStateFlow<Resource<SearchResponse>?>(null)
    val searchResult: StateFlow<Resource<SearchResponse>?> = _searchResult

    private var searchJob: Job = Job()

    suspend fun search() {
        _searchResult.value = Resource.loading()

        searchRepository.getSearchPotraviny(_query.value).let {
            _searchResult.value = it
        }
    }

    fun setNewQueryAndSearch(newValue: String) {
        val oldValue = _query.value
        _query.value = newValue

        if (newValue.isBlank()) {
            _searchResult.value = null
        }

        if (newValue != oldValue) {
            searchJob.cancel()
        }

        if (newValue > oldValue) {
            searchJob = viewModelScope.launch {
                delay(300L)
                search()
            }
            searchJob.start()
        }
    }
}