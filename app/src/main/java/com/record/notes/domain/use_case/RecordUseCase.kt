package com.record.notes.domain.use_case

import com.record.notes.domain.repository.RecordRepository
import com.record.notes.data.common.Resource
import com.record.notes.data.source.local.CustomerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RecordUseCase(private val recordRepository: RecordRepository) {


    // insert customer in local database
    operator fun invoke(customerEntity: CustomerEntity) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = recordRepository.insertCustomer(customerEntity)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}