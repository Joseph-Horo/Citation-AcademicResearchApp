package com.example.explore.presentation.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.explore.presentation.ExploreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ExploreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExploreViewModel::class)
    abstract fun bindExploreViewModel(
        exploreViewModel: ExploreViewModel
    ): ViewModel

}