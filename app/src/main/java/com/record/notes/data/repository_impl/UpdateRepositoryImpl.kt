package com.record.notes.data.repository_impl

import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.data.source.local.RoomDao
import com.record.notes.domain.repository.UpdateRepository

class UpdateRepositoryImpl(private val roomDao: RoomDao) : UpdateRepository {
    override suspend fun updateCustomer(customerEntity: CustomerEntity) {
        try {
            return roomDao.updateCustomer(customerEntity)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}