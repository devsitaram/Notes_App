package com.record.notes.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.record.notes.data.common.Resource
import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.domain.use_case.HomeUseCase
import com.record.notes.presentation.state.CustomerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private var _customerList = mutableStateOf(CustomerState())
    val customerList: State<CustomerState> get() = _customerList

    fun getCustomerDetails() {
        homeUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _customerList.value = CustomerState(isLoading = true)
                }

                is Resource.Success -> {
                    _customerList.value = CustomerState(isData = result.data)
                }

                is Resource.Error -> {
                    _customerList.value = CustomerState(isError = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteCustomer(customerId: Int?){
        CoroutineScope(Dispatchers.IO).async {
            homeUseCase(customerId).launchIn(viewModelScope)
        }
    }
}