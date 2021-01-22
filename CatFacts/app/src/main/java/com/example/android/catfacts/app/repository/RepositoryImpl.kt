package com.example.android.catfacts.app.repository

import androidx.annotation.WorkerThread
import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.api.ICatImgApi
import com.example.android.catfacts.app.db.CatEntity
import com.example.android.catfacts.app.db.ICatDao
import com.example.android.catfacts.app.model.CatFacts
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val catFactsApi: ICatFactsApi,
                     private val catImgApi: ICatImgApi,
                     private val catDao:ICatDao
) :
    IRepository {
    private var data: CatFacts? = null
    private val allCat: Flow<List<CatEntity>> = catDao.getCatFacts()

    override fun getAllCatEntity(): Flow<List<CatEntity>> = allCat

    override suspend fun getCatFacts(): CatFacts? {
        data = catFactsApi.getCatFacts()
        return data
    }

    override suspend fun getCatImgUrl() = catImgApi.getCatImg()
    override fun getCatFactsDataFromCash(): CatFacts? {
        return data
    }
    @WorkerThread
    override suspend fun insert(catEntity: CatEntity) {
        catDao.insert(catEntity)
    }
}