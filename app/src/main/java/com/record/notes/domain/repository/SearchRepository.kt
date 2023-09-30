package com.record.notes.domain.repository

import com.record.notes.domain.model.CustomerPojo

interface SearchRepository {

    suspend fun searchByName(fullName: String?): CustomerPojo?

}