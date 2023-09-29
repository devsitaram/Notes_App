package com.record.notes.domain.model

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Query

data class CustomerPojo(
    val customerId: Int? = null,
    val fullName: String? = null,
    val work: String? = null,
    val amounts: String? = null,
    val status: Boolean? = null, // line ki dine
    val phoneNumber: String? = null,
    val descriptions: String? = null,
    val nickname: String? = null,
    val emailAddress: String? = null,
    val photoUrl: String? = null,
    val location: String? = null,
    val dateAndTime: String? = null
)