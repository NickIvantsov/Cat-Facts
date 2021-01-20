package com.example.android.catfacts.app.fragment.main

import androidx.lifecycle.ViewModel
import com.example.android.catfacts.app.fragment.main.MainFragment.Companion.data
import com.example.android.catfacts.app.repository.IRepository

class MainViewModel(private val repository: IRepository) : ViewModel() {
    suspend fun loadCatFacts() {
        val catFacts = repository.getCatFacts()
        data = catFacts
    }
}