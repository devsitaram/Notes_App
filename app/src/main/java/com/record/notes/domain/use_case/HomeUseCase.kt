package com.record.notes.domain.use_case

import com.record.notes.data.common.Resource
import com.record.notes.domain.model.CustomerPojo
import com.record.notes.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HomeUseCase(private val homeRepository: HomeRepository) {
    operator fun invoke(): Flow<Resource<List<CustomerPojo>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = homeRepository.getCustomerDetails()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    operator fun invoke(customerId: Int?) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = homeRepository.deleteById(customerId)))
        } catch (e: Exception){
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}

//
//    operator fun invoke(userEntity: UserEntity) = flow {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(data = userRepository.insertUserProfile(userEntity)))
//        } catch (e: Exception) {
//            emit(Resource.Error(message = e.message.toString()))
//        }
//    }
//}