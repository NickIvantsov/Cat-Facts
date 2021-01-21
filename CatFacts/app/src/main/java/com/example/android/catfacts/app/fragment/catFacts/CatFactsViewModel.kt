package com.example.android.catfacts.app.fragment.catFacts

import androidx.lifecycle.ViewModel
import com.example.android.catfacts.app.adapter.CatFactsAdapter
import com.example.android.catfacts.app.repository.IRepository

class CatFactsViewModel(private val repository: IRepository) : ViewModel() {
    fun fillAdapter(adapter: CatFactsAdapter) {
        repository.getCatFactsData()?.forEach {
            adapter.add(it)
            adapter.notifyDataSetChanged()
        }
    }
}