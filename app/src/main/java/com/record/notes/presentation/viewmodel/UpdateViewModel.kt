package com.record.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.domain.use_case.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(private val updateUseCase: UpdateUseCase):ViewModel() {

    fun updateCustomer(customerEntity: CustomerEntity){
        updateUseCase(customerEntity).launchIn(viewModelScope)
    }

}