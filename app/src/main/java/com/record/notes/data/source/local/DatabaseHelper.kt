package com.record.notes.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.record.notes.data.common.Constants.DATABASE_NAME
import com.record.notes.data.common.util.JsonTypeConverter

/**
 * this is the Room Database which is abstract class
 * @param entities: list of entities class*
 * @param version
 *        1: create database one subject table @Database
 *        2: add another user table (migrate)
 *        3: update the user table (add the column)
 *        4: new methods implement in dao and entity id auto generate changes
 *        5: adding json type converter @TypeConverters
 * implement RoomDatabase
 */
@Database(entities = [CustomerEntity::class], version = 3, exportSchema = false)
@TypeConverters(JsonTypeConverter::class)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun userDao(): RoomDao

    companion object {
        fun getDatabaseInstance(context: Context): DatabaseHelper {
            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
        }
    }
}