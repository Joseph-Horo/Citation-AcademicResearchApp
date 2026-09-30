package com.example.details.data.di

import com.example.details.data.repository.DetailRepositoryImpl
import com.example.details.domain.repository.DetailRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DetailRepositoryModule {
    @Binds
    abstract fun bindDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ): DetailRepository
}