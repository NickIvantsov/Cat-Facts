package com.example.android.catfacts.app.repository

import android.graphics.Bitmap
import androidx.annotation.WorkerThread
import com.example.android.catfacts.app.db.CatEntity
import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.app.model.CatImg
import com.example.android.catfacts.util.net.AppResult

interface IRepository {
    suspend fun makeCatFactsRequest(): AppResult<CatFacts>
    suspend fun makeCatImgUrlRequest(): CatImg?
    fun getCatFactsDataFromCash(): CatFacts?
    fun getImgBitmapList(): ArrayList<Bitmap?>

    @WorkerThread
    suspend fun insert(catEntity: CatEntity)
}