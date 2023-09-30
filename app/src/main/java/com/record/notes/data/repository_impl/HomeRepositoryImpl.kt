package com.record.notes.data.repository_impl

import android.util.Log
import com.record.notes.data.source.local.RoomDao
import com.record.notes.domain.model.CustomerPojo
import com.record.notes.domain.repository.HomeRepository

class HomeRepositoryImpl(private val roomDao: RoomDao) : HomeRepository {
    override suspend fun getCustomerDetails(): List<CustomerPojo>? {
        return try {
            roomDao.getCustomerDetails()
        } catch (e: Exception){
            throw Exception(e)
        }
    }

    override suspend fun deleteById(customerId: Int?) {
        try {
            return roomDao.deleteById(customerId)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}
//    // login authentications
//    override suspend fun getLoginUserAuth(email: String, password: String): AuthPojo {
//        try {
//            val loginRequestModel = LoginRequestModel(
//                userNameOrEmailAddress = email,
//                password = password,
//                rememberClient = false,
//                couponCode = "",
//            )
//            val response = apiServiceMst.getLoginUserAuth(loginRequestModel)
//            return response ?: throw Exception("Login failed: ${response?.error}")
//        } catch (e: Exception) {
//            // Handle exceptions
//            throw Exception("Login failed: ${e.message}", e)
//        }
//    }
//
//    // get user profiles
//    override suspend fun getUserProfile(): ProfileResult? {
//        try {
//            val profiles = roomDao.getUserProfiles()
//            return if (profiles?.userId != null) {
//                profiles
//            } else {
//                apiServiceMst.getUserProfiles()?.result
//            }
//        } catch (e: Exception) {
//            throw Exception("Response Error: ${e.message}", e)
//        }
//    }
//
//    override suspend fun insertUserProfile(userEntity: UserEntity) {
//        try {
//            roomDao.insertUserProfile(userEntity)
//        } catch (e: Exception) {
//            throw Exception(e)
//        }
//    }
//}