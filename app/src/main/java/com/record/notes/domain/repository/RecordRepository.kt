package com.record.notes.domain.repository

import com.record.notes.data.source.local.CustomerEntity

interface RecordRepository {

////
////    suspend fun getUserProfile(): ProfileResult?
//
    suspend fun insertCustomer(customerEntity: CustomerEntity)
}