package com.record.notes.di.appmodule

import android.content.Context
import com.record.notes.data.source.local.DatabaseHelper.Companion.getDatabaseInstance
import com.record.notes.data.source.local.RoomDao
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

//
//    // get api instance
//    @Provides
//    @Singleton
//    fun provideApiRetrofitInstance(@ApplicationContext context: Context): ApiServiceMst {
//        // get subject retrofit instance mst
//        return getSubjectRetrofitInstance(context).create(ApiServiceMst::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideVideoApiRetrofitInstance(@ApplicationContext context: Context): ApiServiceIvy {
//        // get video retrofit instance ivy
//        return getIvyRetrofitInstance(context).create(ApiServiceIvy::class.java)
//    }
//
//    //user auth and profile
//    @Provides
//    @Singleton
//    fun provideUserRepoImpl(apiServiceMst: ApiServiceMst, roomDao: RoomDao): UserRepository {
//        return UserRepositoryImpl(apiServiceMst, roomDao)
//    }
//
//    // subject
//    @Provides
//    @Singleton
//    fun provideSubjectRepoImpl(apiServiceMst: ApiServiceMst, roomDao: RoomDao): SubjectRepository {
//        return SubjectRepositoryImpl(apiServiceMst, roomDao)
//    }
//
}