package com.example.android.catfacts.app.model


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("sentCount")
    val sentCount: Int = 0,
    @SerializedName("verified")
    val verified: Boolean = false
)