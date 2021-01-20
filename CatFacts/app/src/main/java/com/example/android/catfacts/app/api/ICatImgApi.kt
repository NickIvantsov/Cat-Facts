package com.example.android.catfacts.app.api

import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.app.model.CatImg
import retrofit2.http.GET


val CAT_IMG_URL = "https://aws.random.cat/"

interface ICatImgApi {
    @GET("meow")
    suspend fun getCatImg(): CatImg?
}