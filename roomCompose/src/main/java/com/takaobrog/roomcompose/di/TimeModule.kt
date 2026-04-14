package com.takaobrog.roomcompose.di

import com.takaobrog.roomcompose.util.local_date.TimeProvider
import com.takaobrog.roomcompose.util.local_date.TimeProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TimeModule {
    @Binds
    @Singleton
    abstract fun bindTime(
        impl: TimeProviderImpl,
    ): TimeProvider
}