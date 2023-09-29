package com.record.notes.presentation.state



data class RecordState(
    val isLoading: Boolean = false,
//    var isData: List<SubjectResult?>? = null,
    val isError: String = ""
)