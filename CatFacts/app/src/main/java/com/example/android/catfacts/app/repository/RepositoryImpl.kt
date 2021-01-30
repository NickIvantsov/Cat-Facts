package com.example.android.catfacts.app.repository

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.WorkerThread
import com.example.android.catfacts.app.api.ICatFactsApi
import com.example.android.catfacts.app.api.ICatImgApi
import com.example.android.catfacts.app.db.CatEntity
import com.example.android.catfacts.app.db.ICatDao
import com.example.android.catfacts.app.model.CatFactItem
import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.util.debugTimber
import com.example.android.catfacts.util.net.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RepositoryImpl(
    private val catFactsApi: ICatFactsApi,
    private val catImgApi: ICatImgApi,
    private val catDao: ICatDao,
    private val context: Context
) :
    IRepository {
    private var data: CatFacts = CatFacts()
    private val bitmapList = ArrayList<Bitmap?>()

    override fun getImgBitmapList(): ArrayList<Bitmap?> {
        return bitmapList
    }

    override suspend fun makeCatFactsRequest(): AppResult<CatFacts> {
        data.clear()
        if (isOnline(context)) {
            return try {
                val response = catFactsApi.getCatFacts()
                response.body()?.forEach {catFact->
                    withContext(Dispatchers.IO) {
                        data.add(CatFactItem(text = catFact.text))
                        val catEntity = CatEntity(catFact.text)
                        insert(catEntity)
                    }
                }
                if (response.isSuccessful) {
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            catDao.getCatFacts().forEach {
                if (it.catFacts.isNotEmpty())
                    data.add(CatFactItem(text = it.catFacts))
            }
            return if (data.isNotEmpty()) {
                debugTimber("from db")
                AppResult.Success(data)
            } else
            //no network
                context.noNetworkConnectivityError()
        }
    }

    override suspend fun makeCatImgUrlRequest() = catImgApi.getCatImg()

    override  fun getCatFactsDataFromCash(): CatFacts {
        return data
    }

    override suspend  fun insert(catEntity: CatEntity) {
        catDao.insert(catEntity)
    }
}