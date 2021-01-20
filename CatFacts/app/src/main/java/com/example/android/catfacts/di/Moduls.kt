package com.example.android.catfacts.di

import com.example.android.catfacts.app.adapter.CatFactsAdapter
import com.example.android.catfacts.app.api.CAT_FACTS_BASE_URL
import com.example.android.catfacts.app.api.CAT_IMG_URL
import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.api.ICatImgApi
import com.example.android.catfacts.app.fragment.catFacts.CatFactsViewModel
import com.example.android.catfacts.app.fragment.main.MainViewModel
import com.example.android.catfacts.app.repository.RepositoryImpl
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appMode = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(OkHttpProfilerInterceptor())
            .build()
    }

    single<ICatFactsApi> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(CAT_FACTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ICatFactsApi::class.java)
    }
    single<ICatImgApi> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(CAT_IMG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ICatImgApi::class.java)
    }

    factory {
        CatFactsAdapter(get<RepositoryImpl>())
    }
}

val repositoryModule = module {
    single { RepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get<RepositoryImpl>()) }
    viewModel { CatFactsViewModel(get<RepositoryImpl>()) }
}
