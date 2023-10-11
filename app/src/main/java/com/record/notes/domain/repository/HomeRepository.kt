package com.record.notes.domain.repository

import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.domain.model.CustomerPojo

interface HomeRepository {

    suspend fun getCustomerDetails(): List<CustomerPojo>?

    suspend fun deleteById(customerId: Int?)
}