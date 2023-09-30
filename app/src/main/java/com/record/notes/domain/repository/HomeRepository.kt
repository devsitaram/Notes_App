package com.record.notes.domain.repository

import com.record.notes.domain.model.CustomerPojo

interface HomeRepository {

    suspend fun getCustomerDetails(): List<CustomerPojo>?
//
//    suspend fun insertSubject(listOfSubject: List<SubjectEntity>)

    suspend fun deleteById(customerId: Int?)
}