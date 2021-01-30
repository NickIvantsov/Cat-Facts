package com.example.android.catfacts.util.net

sealed class AppResult<out T> {

    data class Success<out T>(val successData : T) : AppResult<T>()
    class Error(val exception: Exception) : AppResult<Nothing>()
}