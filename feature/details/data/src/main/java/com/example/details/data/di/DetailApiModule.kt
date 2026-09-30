package com.example.details.data.di

import com.example.details.data.remote.DetailApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object DetailApiModule {
    @Provides
    @Singleton
    fun provideDetailApi(retrofit: Retrofit): DetailApi{
        return retrofit.create(DetailApi::class.java)
    }
}