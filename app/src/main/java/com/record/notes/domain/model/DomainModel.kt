package com.record.notes.domain.model

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Query

data class CustomerPojo(
    val customerId: Int? = null,
    val dateAndTime: String? = null,
    val fullName: String? = null,
    val work: String? = null,
    val amounts: String? = null,
    val status: String? = null, // line ki dine
    val descriptions: String? = null,
    val phoneNumber: String? = null,
    val nickname: String? = null,
    val location: String? = null,
    val emailAddress: String? = null,
)