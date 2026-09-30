package com.example.home.data.di

import com.example.home.data.remote.HomeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
object HomeApiModule {
    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi{
        return retrofit.create(HomeApi::class.java)
    }
}