package com.record.notes.domain.repository

import com.record.notes.data.source.local.CustomerEntity

interface UpdateRepository {
    suspend fun updateCustomer(customerEntity: CustomerEntity)

}