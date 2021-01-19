package com.example.android.catfacts.app.model


import com.google.gson.annotations.SerializedName

data class CatFactItem(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("deleted")
    val deleted: Boolean = false,
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("source")
    val source: String = "",
    @SerializedName("status")
    val status: Status = Status(),
    @SerializedName("text")
    val text: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("used")
    val used: Boolean = false,
    @SerializedName("user")
    val user: String = "",
    @SerializedName("__v")
    val v: Int = 0
)