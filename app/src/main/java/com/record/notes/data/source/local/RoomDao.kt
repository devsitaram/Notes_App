package com.record.notes.data.source.local

import androidx.room.Dao
import androidx.room.Upsert

@SuppressWarnings("AndroidUnresolvedRoomSqlReference")
@Dao
interface RoomDao {

    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun insertUserProfile(customerEntity: CustomerEntity)

//    @Query("SELECT * FROM user")
//    suspend fun getUserProfiles(): ListOfUserDetails?
//
//    @Upsert // insert and update both
//    suspend fun insertSubject(listOfSubject: List<SubjectEntity>)
//
//    @Query("SELECT * FROM subjects")
//    suspend fun getSubject(): List<SubjectResult>?
//
//    @Query("DELETE FROM subjects WHERE subjectId = :id")
//    suspend fun deleteSubject(id: Int)


}