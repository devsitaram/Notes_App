package com.record.notes.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("customerId")
    val customerId: Int? = null,
    @ColumnInfo("dateAndTime")
    val dateAndTime: String? = null,
    @ColumnInfo("fullName")
    val fullName: String? = null,
    @ColumnInfo("work")
    val work: String? = null,
    @ColumnInfo("amounts")
    val amounts: String? = null,
    @ColumnInfo("status") // line ki dine
    val status: String? = null,
    @ColumnInfo("descriptions")
    val descriptions: String? = null,
    @ColumnInfo("phoneNumber")
    val phoneNumber: String? = null,
    @ColumnInfo("nickname")
    val nickname: String? = null,
    @ColumnInfo("location")
    val location: String? = null,
    @ColumnInfo("emailAddress")
    val emailAddress: String? = null
)