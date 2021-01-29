package com.example.android.catfacts.app.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val goToMainLiveData = MutableLiveData<Boolean>()
    fun goToMainScreen() {
        GlobalScope.launch {
            delay(3000)
            withContext(Dispatchers.Main) {
                goToMainLiveData.value = true
            }
        }
    }

    fun getMainLiveData(): LiveData<Boolean> {
        return goToMainLiveData
    }
}