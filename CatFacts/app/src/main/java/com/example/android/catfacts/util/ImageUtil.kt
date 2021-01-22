package com.example.android.catfacts.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


fun convert(base64Str: String): Bitmap? {
    val decodedBytes: ByteArray = Base64.decode(
        base64Str.substring(base64Str.indexOf(",") + 1),
        Base64.DEFAULT
    )
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}

fun convert(bitmap: Bitmap): String? {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
}

fun getByteArrayFromBitmap(bitmap: Bitmap): ByteArray? {
    val bos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
    return bos.toByteArray()
}

fun getBitmapFromByteArray(bitmap: ByteArray): Bitmap? {
    return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.size)
}