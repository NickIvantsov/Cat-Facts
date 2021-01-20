package com.example.android.catfacts.app.repository

import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.app.model.CatImg

interface IRepository {
    suspend fun getCatFacts(): CatFacts?
    suspend fun getCatImgUrl(): CatImg?
}