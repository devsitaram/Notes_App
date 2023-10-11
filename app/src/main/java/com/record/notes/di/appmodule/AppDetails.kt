package com.record.notes.di.appmodule

import com.record.notes.domain.repository.HomeRepository
import com.record.notes.domain.repository.RecordRepository
import com.record.notes.domain.repository.SearchRepository
import com.record.notes.domain.repository.UpdateRepository
import com.record.notes.domain.use_case.HomeUseCase
import com.record.notes.domain.use_case.RecordUseCase
import com.record.notes.domain.use_case.SearchUseCase
import com.record.notes.domain.use_case.UpdateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDetails {

    @Provides
    @Singleton
    fun provideCustomerUseCase(homeRepository: HomeRepository): HomeUseCase {
        return HomeUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideRecordUseCase(recordRepository: RecordRepository): RecordUseCase {
        return RecordUseCase(recordRepository)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchUseCase{
        return SearchUseCase(searchRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateUseCase(updateRepository: UpdateRepository): UpdateUseCase{
        return UpdateUseCase(updateRepository)
    }
}