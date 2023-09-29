package com.record.notes.domain.use_case

//import com.record.notes.data.common.Resource
//import com.record.notes.data.source.local.UserEntity
//import com.record.notes.domain.repository.UserRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import java.lang.Exception
//
//class UserProfileUseCase(private val userRepository: UserRepository) {
//
//    operator fun invoke(): Flow<Resource<ProfileResult>> = flow {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(data = userRepository.getUserProfile()))
//        } catch (e: Exception) {
//            emit(Resource.Error(message = e.message.toString()))
//        }
//    }
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