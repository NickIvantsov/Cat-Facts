package com.example.android.catfacts.app.fragment.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.app.model.CatImg
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.util.convert
import com.example.android.catfacts.util.errorTimber
import com.example.android.catfacts.util.net.AppResult
import com.example.android.catfacts.util.net.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.net.URL


class MainViewModel(private val repository: IRepository, private val context: Context) :
    ViewModel() {
    var alpha = 1F
        private set
    var isClickable = true
        private set

    fun enableBtn() {
        alpha = 1F
        isClickable = true
    }

    fun disableBtn() {
        alpha = 0.3F
        isClickable = false
    }

    suspend fun loadCatFacts(): Boolean {
        clearImageList()
        return withContext(Dispatchers.IO) {
            when (val result = repository.makeCatFactsRequest()) {
                is AppResult.Success -> {
                    loadPhoto(result)
                    true
                }
                is AppResult.Error -> {
                    errorTimber(result.exception)
                    false
                }
            }
        }
    }

    private suspend fun loadPhoto(result: AppResult.Success<CatFacts>) {
        var count = 0
        val catFactsList: CatFacts = result.successData
        catFactsList.forEach { _ ->
            try {
                if (isOnline(context)) {
                    val catUrl = repository.makeCatImgUrlRequest()
                    saveImg(catUrl, count)
                } else {
                    takePhotoFromCash(count)
                }
            } catch (e: Exception) {
                errorTimber(e)
            }
            ++count
        }
    }

    private fun saveImg(catUrl: CatImg?, count: Int) {
        val `in`: InputStream = URL(catUrl!!.file).openStream()
        val icon: Bitmap = BitmapFactory.decodeStream(`in`)
        repository.getImgBitmapList().add(icon)

        val filename = "cat_photo_$count"
        val fileContents = convert(icon)!!
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }

    private fun takePhotoFromCash(count: Int) {
        val allText =
            context.openFileInput("cat_photo_$count").bufferedReader().use(BufferedReader::readText)
        repository.getImgBitmapList().add(convert(allText))
    }

    private fun clearImageList() {
        repository.getImgBitmapList().clear()
    }
}