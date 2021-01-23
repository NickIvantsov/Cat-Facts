package com.example.android.catfacts.app.fragment.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.catfacts.app.db.CatEntity
import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.app.model.CatImg
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.util.convert
import com.example.android.catfacts.util.errorTimber
import com.example.android.catfacts.util.net.AppResult
import com.example.android.catfacts.util.net.isOnline
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStream
import java.net.URL


class MainViewModel(private val repository: IRepository, private val context: Context) :
    ViewModel() {
    var alpha = 1F
    var isClickable = true

    suspend fun loadCatFacts():Boolean {
        clearImageList()
        when (val result = repository.getCatFacts()) {
            is AppResult.Success -> {
                var count = 0
                val catFactsList: CatFacts = result.successData
                catFactsList.forEach { catFact ->
                    try {
                        if (isOnline(context)) {
                            val catUrl = repository.getCatImgUrl()
                            saveImg(catUrl, count)
                            val catEntity = CatEntity(catFact.text)
                            insert(catEntity)
                        } else {
                            takePhotoFromCash(count)
                        }

                    } catch (e: Exception) {
                        errorTimber(e)
                    }
                    ++count
                }
                return true
            }
            is AppResult.Error -> {
                return false
            }
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

    private fun insert(catEntity: CatEntity) = viewModelScope.launch {
        repository.insert(catEntity)
    }
}