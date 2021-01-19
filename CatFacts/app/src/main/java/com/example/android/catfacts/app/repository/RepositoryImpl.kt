package com.example.android.catfacts.app.repository

import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.model.CatFacts

class RepositoryImpl(private val catFactsApi: ICatFactsApi) :IRepository{
    override suspend fun getCatFacts(): CatFacts? {
       return catFactsApi.getCatFacts()
    }
}