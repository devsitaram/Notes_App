package com.record.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.domain.use_case.RecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(private val recordUseCase: RecordUseCase) : ViewModel() {

    fun registerDetails(customerEntity: CustomerEntity){
        recordUseCase(customerEntity).launchIn(viewModelScope)
    }
}