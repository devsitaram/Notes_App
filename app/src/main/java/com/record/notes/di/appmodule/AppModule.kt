package com.record.notes.di.appmodule

import android.content.Context
import androidx.room.Update
import com.record.notes.data.repository_impl.HomeRepositoryImpl
import com.record.notes.data.repository_impl.RecordRepositoryImpl
import com.record.notes.data.repository_impl.SearchRepositoryImpl
import com.record.notes.data.repository_impl.UpdateRepositoryImpl
import com.record.notes.data.source.local.DatabaseHelper.Companion.getDatabaseInstance
import com.record.notes.data.source.local.RoomDao
import com.record.notes.domain.repository.HomeRepository
import com.record.notes.domain.repository.RecordRepository
import com.record.notes.domain.repository.SearchRepository
import com.record.notes.domain.repository.UpdateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // get room database instance
    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context): RoomDao {
         return getDatabaseInstance(context).userDao()
    }

    // subject
    @Provides
    @Singleton
    fun provideSubjectRepoImpl(roomDao: RoomDao): RecordRepository {
        return RecordRepositoryImpl(roomDao)
    }

    //user auth and profile
    @Provides
    @Singleton
    fun provideUserRepoImpl(roomDao: RoomDao): HomeRepository {
        return HomeRepositoryImpl(roomDao)
    }

    @Provides
    @Singleton
    fun provideSearchRepoImpl(roomDao: RoomDao): SearchRepository {
        return SearchRepositoryImpl(roomDao)
    }

    @Provides
    @Singleton
    fun provideUpdateRepoImpl(roomDao: RoomDao): UpdateRepository {
        return UpdateRepositoryImpl(roomDao)
    }
}