package com.record.notes.presentation.state

import com.record.notes.domain.model.CustomerPojo


data class CustomerState(
    val isLoading: Boolean = false,
    var isData: List<CustomerPojo?>? = null,
    val isError: String = ""
)