package com.record.notes.presentation.state


data class ProfileState(
    val isLoading: Boolean = false,
//    val isData: ProfileResult? = null,
    val isError: String = ""
)

//data class ProfileState(
//    val isLoading: Boolean = false,
//    val isData: UserPojo? = null,
//    val isError: String = ""
//)