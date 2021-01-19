package com.example.android.catfacts.app

import android.app.Application
import com.example.android.catfacts.BuildConfig
import com.example.android.catfacts.di.appMode
import com.example.android.catfacts.di.repositoryModule
import com.example.android.catfacts.di.viewModelModule
import com.example.android.catfacts.util.ReleaseThree
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(androidContext = this, listOf(appMode, viewModelModule, repositoryModule))
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseThree())
        }
    }
}