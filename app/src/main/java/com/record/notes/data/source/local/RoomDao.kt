package com.record.notes.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.record.notes.domain.model.CustomerPojo

@SuppressWarnings("AndroidUnresolvedRoomSqlReference")
@Dao
interface RoomDao {

    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun insertCustomerDetails(customerEntity: CustomerEntity)

    @Query("SELECT * FROM customer")
    suspend fun getCustomerDetails(): List<CustomerPojo>?

    @Query("DELETE FROM customer WHERE customerId = :customerId")
    suspend fun deleteById(customerId: Int?)

    @Query("select * from customer where fullName = :fullName")
    suspend fun searchByName(fullName: String?): CustomerPojo?

//    @Upsert // insert and update both
//    suspend fun insertSubject(listOfSubject: List<SubjectEntity>)
//
//    @Query("SELECT * FROM subjects")
//    suspend fun getSubject(): List<SubjectResult>?
//
//    @Query("DELETE FROM subjects WHERE subjectId = :id")
//    suspend fun deleteSubject(id: Int)


}