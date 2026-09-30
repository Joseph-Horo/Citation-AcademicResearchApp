package com.example.core.database.di

import android.app.Application
import androidx.room.Room
import com.example.core.database.CitationDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CitationDatabaseModule {
    @Provides
    @Singleton
    fun provideCitationDatabase(app: Application): CitationDatabase{
        return Room.databaseBuilder(app, CitationDatabase::class.java, "citation.db")
            .build()
    }
}