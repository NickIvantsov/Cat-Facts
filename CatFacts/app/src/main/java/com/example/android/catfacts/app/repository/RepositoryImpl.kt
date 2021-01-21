package com.example.android.catfacts.app.repository

import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.api.ICatImgApi
import com.example.android.catfacts.app.model.CatFacts

class RepositoryImpl(private val catFactsApi: ICatFactsApi, private val catImgApi: ICatImgApi) :
    IRepository {
    private var data: CatFacts? = null

    override suspend fun getCatFacts(): CatFacts? {
        data = catFactsApi.getCatFacts()
        return data
    }

    override suspend fun getCatImgUrl() = catImgApi.getCatImg()
    override fun getCatFactsData(): CatFacts? {
        return data
    }
}