package com.record.notes.data.repository_impl

import android.util.Log
import com.record.notes.data.source.local.CustomerEntity
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
            Log.e("Delete id", "Delete id: $customerId")
            return roomDao.deleteById(customerId)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}