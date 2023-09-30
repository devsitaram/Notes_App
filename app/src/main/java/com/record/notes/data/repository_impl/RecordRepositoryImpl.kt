package com.record.notes.data.repository_impl

import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.data.source.local.RoomDao
import com.record.notes.domain.repository.RecordRepository

class RecordRepositoryImpl(private val roomDao: RoomDao) : RecordRepository {
    //    override suspend fun getSubjects(): List<SubjectResult>? {
//        try {
//            val listOfSubject = roomDao.getSubject()
//            return listOfSubject?.ifEmpty {
//                apiServiceMst.getSubjects().result?.map { it }
//            }
//        } catch (e: Exception) {
//            throw Exception(e)
//        }
//    }
//
//    override suspend fun insertSubject(listOfSubject: List<SubjectEntity>) {
//        try {
//            roomDao.insertSubject(listOfSubject = listOfSubject)
//        } catch (e: Exception) {
//            throw Exception("Error")
//        }
//    }
//
//    override suspend fun deleteSubject(id: Int) {
//        try {
//            return roomDao.deleteSubject(id)
//        } catch (e: Exception) {
//            throw Exception(e)
//        }
//    }
    override suspend fun insertCustomer(customerEntity: CustomerEntity) {
        roomDao.insertCustomerDetails(customerEntity)
    }
}