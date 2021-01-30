package com.example.android.catfacts.di

import android.app.Application
import android.content.Context
import com.example.android.catfacts.app.activity.SplashViewModel
import com.example.android.catfacts.app.fragment.catFacts.CatFactsViewModel
import com.example.android.catfacts.app.fragment.main.MainViewModel
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.app.repository.RepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    fun provideMainViewModel(repository: IRepository, context: Context): MainViewModel {
        return MainViewModel(repository, context)
    }

    fun provideCatFactsViewModel(repository: IRepository): CatFactsViewModel {
        return CatFactsViewModel(repository)
    }

    fun provideSplashViewModel(application: Application): SplashViewModel {
        return SplashViewModel(application)
    }
    viewModel { MainViewModel(get<RepositoryImpl>(), androidContext()) }
    viewModel { CatFactsViewModel(get<RepositoryImpl>()) }
    viewModel { SplashViewModel(androidApplication()) }
}