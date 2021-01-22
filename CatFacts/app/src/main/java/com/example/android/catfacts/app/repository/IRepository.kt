package com.example.android.catfacts.app.repository

import androidx.annotation.WorkerThread
import com.example.android.catfacts.app.db.CatEntity
import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.app.model.CatImg
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getCatFacts(): CatFacts?
    suspend fun getCatImgUrl(): CatImg?
    fun getCatFactsDataFromCash(): CatFacts?

    @WorkerThread
    suspend fun insert(catEntity: CatEntity)
    fun getAllCatEntity(): Flow<List<CatEntity>>
}