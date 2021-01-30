package com.example.android.catfacts.di

import com.example.android.catfacts.app.api.CAT_FACTS_BASE_URL
import com.example.android.catfacts.app.api.CAT_IMG_URL
import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.api.ICatImgApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    fun provideCatFactsApi(): ICatFactsApi {
        return Retrofit.Builder()
            .client(get())
            .baseUrl(CAT_FACTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ICatFactsApi::class.java)
    }

    fun provideCatImgApi(): ICatImgApi {
        return Retrofit.Builder()
            .client(get())
            .baseUrl(CAT_IMG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ICatImgApi::class.java)
    }

    single {
        provideCatFactsApi()
    }
    single {
        provideCatImgApi()
    }

}