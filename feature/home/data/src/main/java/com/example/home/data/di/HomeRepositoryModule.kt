package com.example.home.data.di

import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class HomeRepositoryModule {
    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}