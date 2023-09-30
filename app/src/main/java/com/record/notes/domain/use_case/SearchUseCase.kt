package com.record.notes.domain.use_case

import com.record.notes.data.common.Resource
import com.record.notes.domain.model.CustomerPojo
import com.record.notes.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchUseCase(private val searchRepository: SearchRepository) {
    operator fun invoke(fullName: String?): Flow<Resource<CustomerPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = searchRepository.searchByName(fullName)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}