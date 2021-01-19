package com.example.android.catfacts.app.repository

import com.example.android.catfacts.app.model.CatFacts

interface IRepository {

    suspend fun getCatFacts(): CatFacts?
}