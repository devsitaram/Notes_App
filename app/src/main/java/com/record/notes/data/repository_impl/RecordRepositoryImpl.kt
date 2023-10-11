package com.record.notes.data.repository_impl

import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.data.source.local.RoomDao
import com.record.notes.domain.repository.RecordRepository

class RecordRepositoryImpl(private val roomDao: RoomDao) : RecordRepository {
    override suspend fun insertCustomer(customerEntity: CustomerEntity) {
        roomDao.recordDetails(customerEntity)
    }
}