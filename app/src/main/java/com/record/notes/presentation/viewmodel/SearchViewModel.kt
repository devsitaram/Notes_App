package com.record.notes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.record.notes.data.common.Resource
import com.record.notes.domain.use_case.SearchUseCase
import com.record.notes.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase): ViewModel() {

    private var _searchCustomer = mutableStateOf(SearchState())
    val searchCustomer: State<SearchState> get() = _searchCustomer

    private val _query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _query.debounce(1000).collectLatest {
                if (_query.toString().isNotEmpty()) {
                    getSearchCustomer(fullName = it)
                }
            }
        }
    }

    fun searchQuery(query: String){
        _query.value = query
    }

    private fun getSearchCustomer(fullName: String?) {
        searchUseCase(fullName).onEach {
            when (it) {
                is Resource.Loading -> {
                    _searchCustomer.value = SearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _searchCustomer.value = SearchState(isData = it.data)
                }
                is Resource.Error -> {
                    _searchCustomer.value = SearchState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}