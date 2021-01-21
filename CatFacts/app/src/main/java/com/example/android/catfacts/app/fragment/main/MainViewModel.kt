package com.example.android.catfacts.app.fragment.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.util.errorTimber
import java.io.InputStream
import java.net.URL


class MainViewModel(private val repository: IRepository) : ViewModel() {
    companion object {
        val bitmapList = ArrayList<Bitmap?>()
    }

    suspend fun loadCatFacts() {
        val catFacts = repository.getCatFacts()
        catFacts?.forEach { _ ->
            try {
                val catUrl = repository.getCatImgUrl()
                val `in`: InputStream = URL(catUrl!!.file).openStream()
                val icon: Bitmap = BitmapFactory.decodeStream(`in`)
                bitmapList.add(icon)
            } catch (e: Exception) {
                errorTimber(e)
            }

        }
    }
}