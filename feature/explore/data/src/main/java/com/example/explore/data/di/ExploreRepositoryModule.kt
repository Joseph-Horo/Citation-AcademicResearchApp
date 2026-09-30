package com.example.explore.data.di

import com.example.explore.data.repository.ExploreRepositoryImpl
import com.example.explore.domain.repository.ExploreRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ExploreRepositoryModule {
    @Binds
    abstract fun bindExploreRepository(
        exploreRepositoryImpl: ExploreRepositoryImpl
    ): ExploreRepository
}