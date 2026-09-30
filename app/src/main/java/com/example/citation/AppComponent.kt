package com.example.citation

import android.app.Application

import com.example.core.database.di.CitationDatabaseModule
import com.example.core.di.ViewModelFactory
import com.example.core.network.NetworkModule
import com.example.details.data.di.DetailApiModule
import com.example.details.data.di.DetailRepositoryModule
import com.example.details.presentation.di.DetailViewModelModule
import com.example.explore.data.di.ExploreApiModule
import com.example.explore.data.di.ExploreRepositoryModule
import com.example.explore.presentation.di.ExploreViewModelModule
import com.example.home.data.di.HomeApiModule
import com.example.home.data.di.HomeRepositoryModule
import com.example.home.presentation.di.HomeViewModelModule
import com.example.watchlist.data.di.WatchListRepositoryModule
import com.example.watchlist.presentation.di.WatchListViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ExploreViewModelModule::class,
        ExploreRepositoryModule::class,
        ExploreApiModule::class,
        DetailApiModule::class,
        DetailRepositoryModule::class,
        DetailViewModelModule::class,
        WatchListViewModelModule::class,
        WatchListRepositoryModule::class,
        HomeViewModelModule::class,
        HomeRepositoryModule::class,
        HomeApiModule::class,
        NetworkModule::class,
        CitationDatabaseModule::class

    ]
)
interface AppComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application): AppComponent
    }
}