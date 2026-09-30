package com.example.watchlist.data.di

import com.example.watchlist.data.repository.WatchListRepositoryImpl
import com.example.watchlist.domain.repository.WatchListRepository
import dagger.Binds
import dagger.Module

@Module
abstract class WatchListRepositoryModule {
    @Binds
    abstract fun bindWatchListRepository(
        watchListRepositoryImpl: WatchListRepositoryImpl
    ): WatchListRepository
}