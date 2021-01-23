package com.example.android.catfacts.app.fragment.catFacts

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.android.catfacts.app.adapter.CatFactsAdapter
import com.example.android.catfacts.app.model.CatFacts
import com.example.android.catfacts.app.repository.IRepository

class CatFactsViewModel(private val repository: IRepository) : ViewModel() {
    suspend fun fillAdapter(adapter: CatFactsAdapter) {
        repository.getCatFactsDataFromCash()?.forEach {
            adapter.add(it)
            adapter.notifyDataSetChanged()
        }
    }
    fun getImgBitmapList():ArrayList<Bitmap?>{
       return repository.getImgBitmapList()
    }
    suspend fun getCatFactsList():CatFacts?{
       return repository.getCatFactsDataFromCash()
    }
}