package com.example.android.catfacts.di

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module.module
import java.util.concurrent.TimeUnit

val networkModule = module {
    val connectTimeout: Long = 40
    val readTimeout: Long = 40

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient()
            .newBuilder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .addInterceptor(OkHttpProfilerInterceptor())
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor())
        }

        return okHttpClientBuilder.build()
    }
    single { provideHttpClient() }
}