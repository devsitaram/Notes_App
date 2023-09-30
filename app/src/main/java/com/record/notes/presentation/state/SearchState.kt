package com.record.notes.presentation.state

import com.record.notes.domain.model.CustomerPojo

data class SearchState (
    val isLoading: Boolean = false,
    var isData: CustomerPojo? = null,
    val isError: String = ""
)