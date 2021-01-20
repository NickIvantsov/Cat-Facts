package com.example.android.catfacts.app.repository

import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.api.ICatImgApi
import com.example.android.catfacts.app.model.CatFacts

class RepositoryImpl(private val catFactsApi: ICatFactsApi, private val catImgApi: ICatImgApi) :
    IRepository {
    override suspend fun getCatFacts() = catFactsApi.getCatFacts()

    override suspend fun getCatImgUrl() = catImgApi.getCatImg()
}