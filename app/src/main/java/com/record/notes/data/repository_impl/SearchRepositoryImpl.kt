package com.record.notes.data.repository_impl

import com.record.notes.data.source.local.RoomDao
import com.record.notes.domain.model.CustomerPojo
import com.record.notes.domain.repository.SearchRepository

class SearchRepositoryImpl(private val roomDao: RoomDao) : SearchRepository {
    override suspend fun searchByName(fullName: String?): CustomerPojo? {
        return try {
            roomDao.searchByName(fullName)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}