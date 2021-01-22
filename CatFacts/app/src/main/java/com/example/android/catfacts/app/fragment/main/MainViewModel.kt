package com.example.android.catfacts.app.fragment.main

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.catfacts.app.db.CatEntity
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.util.convert
import com.example.android.catfacts.util.errorTimber
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL


class MainViewModel(private val repository: IRepository) : ViewModel() {
    companion object {
        val bitmapList = ArrayList<Bitmap?>()
    }

    val allCatEntity: LiveData<List<CatEntity>> = repository.getAllCatEntity().asLiveData()

    suspend fun loadCatFacts() {
        val catFacts = repository.getCatFacts()
        catFacts?.forEach { catFact ->
            try {
                val catUrl = repository.getCatImgUrl()
                val `in`: InputStream = URL(catUrl!!.file).openStream()
                val icon: Bitmap = BitmapFactory.decodeStream(`in`)
                bitmapList.add(icon)
                val catEntity = CatEntity(catFact.text, convert(icon)!!)
                insert(catEntity)
            } catch (e: Exception) {
                errorTimber(e)
            }

        }
    }

    fun insert(catEntity: CatEntity) = viewModelScope.launch {
        repository.insert(catEntity)
    }
}

fun getByteArrayfromBitmap(bitmap: Bitmap): ByteArray? {
    val bos = ByteArrayOutputStream()
    bitmap.compress(CompressFormat.PNG, 0, bos)
    return bos.toByteArray()
}

fun getBitmapfromByteArray(bitmap: ByteArray): Bitmap? {
    return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.size)
}