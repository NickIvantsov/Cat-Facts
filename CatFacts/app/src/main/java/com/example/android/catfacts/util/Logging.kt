package com.example.android.catfacts.util

import timber.log.Timber

val errorTimber = { ex: Throwable ->
    Timber.e("catch: $ex")
}
val debugTimber = { msg: String ->
    Timber.d(msg)
}