package com.record.notes.di.appmodule

import com.record.notes.domain.repository.RecordRepository
import com.record.notes.domain.use_case.RecordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDetails {

//    @Provides
//    @Singleton
//    fun provideProfileUseCase(userRepository: UserRepository): UserProfileUseCase {
//        return UserProfileUseCase(userRepository)
//    }

    @Provides
    @Singleton
    fun provideSubjectUseCase(recordRepository: RecordRepository): RecordUseCase {
        return RecordUseCase(recordRepository)
    }
}