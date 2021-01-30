package com.example.android.catfacts.di

import android.app.Application
import androidx.room.Room
import com.example.android.catfacts.app.db.CatFactsDatabase
import com.example.android.catfacts.app.db.ICatDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {

    fun provideDatabase(application: Application): CatFactsDatabase {
        return Room.databaseBuilder(application, CatFactsDatabase::class.java, "cat_facts")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCatDao(database: CatFactsDatabase): ICatDao {
        return database.catFactsDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCatDao(get()) }
}