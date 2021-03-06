package com.example.android.catfacts.app.api

import com.example.android.catfacts.app.model.CatFacts
import retrofit2.Response
import retrofit2.http.GET


const val CAT_FACTS_BASE_URL = "https://cat-fact.herokuapp.com"

interface ICatFactsApi {

    @GET("facts")
    suspend fun getCatFacts(): Response<CatFacts>
}