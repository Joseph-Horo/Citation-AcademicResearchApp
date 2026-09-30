package com.example.explore.data.di

import com.example.explore.data.remote.ExploreApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ExploreApiModule {
    @Provides
    @Singleton

    fun provideExploreApi(retrofit: Retrofit): ExploreApi{
       return retrofit.create(ExploreApi::class.java)
    }
}