package com.example.cryptocurrency.di

import com.example.cryptocurrency.data.RepositoryDataBase
import com.example.cryptocurrency.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
 //   fun provideRepository(repository: RepositoryImpl): Repository
    fun provideRepository(repository: RepositoryDataBase): Repository

}