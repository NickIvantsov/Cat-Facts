package com.example.android.catfacts.app.model


import com.google.gson.annotations.SerializedName

data class CatImg(
    @SerializedName("file")
    val `file`: String = ""
)