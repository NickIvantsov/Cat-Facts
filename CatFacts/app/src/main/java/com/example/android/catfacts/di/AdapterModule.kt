package com.example.android.catfacts.di

import android.content.Context
import com.example.android.catfacts.app.adapter.CatFactsAdapter
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.app.repository.RepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val adapterModule = module {
    fun provideCatFactsAdapter(
        repository: IRepository,
        context: Context
    ): CatFactsAdapter {
        return CatFactsAdapter(repository, context)
    }
    factory {
        provideCatFactsAdapter(get<RepositoryImpl>(), androidContext())
    }
}
