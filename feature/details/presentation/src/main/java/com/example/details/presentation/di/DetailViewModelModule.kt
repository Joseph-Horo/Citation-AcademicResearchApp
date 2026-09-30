package com.example.details.presentation.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelAssistedFactory
import com.example.core.di.ViewModelKey
import com.example.details.presentation.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModelFactory(
      factory: DetailViewModel.Factory
    ): ViewModelAssistedFactory<*>
}
