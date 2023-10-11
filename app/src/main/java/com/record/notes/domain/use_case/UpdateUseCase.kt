package com.record.notes.domain.use_case

import com.record.notes.data.common.Resource
import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.domain.repository.UpdateRepository
import kotlinx.coroutines.flow.flow

class UpdateUseCase(private val updateRepository: UpdateRepository) {
    operator fun invoke(customerEntity: CustomerEntity) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = updateRepository.updateCustomer(customerEntity)))
        } catch (e: Exception){
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}