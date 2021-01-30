package com.example.android.catfacts.di

import android.content.Context
import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.api.ICatImgApi
import com.example.android.catfacts.app.db.ICatDao
import com.example.android.catfacts.app.repository.RepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val repositoryModule = module {

    fun provideCountryRepository(
        catFactsApi: ICatFactsApi,
        catImgApi: ICatImgApi,
        catDao: ICatDao,
        context: Context
    ): RepositoryImpl {
        return RepositoryImpl(catFactsApi, catImgApi, catDao, context)
    }
    single { provideCountryRepository(get(), get(), get(), androidContext()) }

}