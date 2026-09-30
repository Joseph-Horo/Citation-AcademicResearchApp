package com.example.core.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
    private val assistedCreators: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModelAssistedFactory<*>>
): ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val assistedFactory = assistedCreators[modelClass]
        if (assistedFactory != null){
            @Suppress("UNCHECKED_CAST")
            return assistedFactory.create(extras.createSavedStateHandle()) as T
        }
        return create(modelClass)
    }
}