package com.example.watchlist.presentation.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.watchlist.presentation.WatchListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WatchListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WatchListViewModel::class)
    abstract fun bindWatchListViewModel(
        watchListViewModel: WatchListViewModel
    ): ViewModel
}