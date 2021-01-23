package com.example.android.catfacts.core

import android.app.Application
import com.example.android.catfacts.BuildConfig
import com.example.android.catfacts.di.appMode
import com.example.android.catfacts.di.databaseModule
import com.example.android.catfacts.di.repositoryModule
import com.example.android.catfacts.di.viewModelModule
import com.example.android.catfacts.util.ReleaseThree
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(androidContext = this, listOf(appMode, viewModelModule, repositoryModule,databaseModule))
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseThree())
        }

        // Create an InitializerBuilder
        val initializerBuilder = Stetho.newInitializerBuilder(applicationContext)

        // Enable Chrome DevTools

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
            Stetho.defaultInspectorModulesProvider(applicationContext)
        )

        // Enable command line interface

        // Enable command line interface
        initializerBuilder.enableDumpapp(
            Stetho.defaultDumperPluginsProvider(applicationContext)
        )

        // Use the InitializerBuilder to generate an Initializer

        // Use the InitializerBuilder to generate an Initializer
        val initializer = initializerBuilder.build()

        // Initialize Stetho with the Initializer

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer)
    }
}